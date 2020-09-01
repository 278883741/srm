package com.imooc.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.model.*;
import com.imooc.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.*;

@Controller
@RequestMapping(value = "/card")
@Api(value = "卡牌接口", tags = {"卡牌接口"})
public class CardController {
    @Autowired
    CardService cardService;
    @Autowired
    CardKindService cardKindService;
    @Autowired
    CardOccupationService cardOccupationService;
    @Autowired
    CardRarityService cardRarityService;
    @Autowired
    CardSetService cardSetService;
    @Autowired
    CardTypeService cardTypeService;
    @Autowired
    CardPropertyService cardPropertyService;

    // 这里的url加不加/都可以
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        getCardInfoExtra(model);

        return "card/list";
        // forward - 转发，是带model数据的，而redirect是不带的
        // 这个是找绝对的url
        // return "forward:/list";
        // 这个是找相对的url，比如两个url都叫/list,那找的是自己controller下的
        // return "forward:list";
    }

    /**
     * 分页获取卡牌数据
     */
    @RequestMapping(value = "/list/query",method = RequestMethod.GET)
    @ResponseBody
    public BasePageModel get(Card card, PageParam pageParam,BasePageModel basePageModel){
        String[] str = new String[]{"","id", "paramName", "typeDisplay", "occupationDisplay", "rarityDisplay", "kindDisplay", "Cost"};
        Page<?> page = PageHelper.startPage(pageParam.getiDisplayStart() / pageParam.getiDisplayLength() + 1, pageParam.getiDisplayLength(),str[pageParam.getiSortCol_0()] + " " + pageParam.getsSortDir_0());

        List<Map<String,Object>> list = cardService.getListDisplay(card);

        basePageModel.setAaData(page);
        basePageModel.setiTotalDisplayRecords((int) page.getTotal());
        basePageModel.setiTotalRecords((int) page.getTotal());
        basePageModel.setRecords((int) page.getTotal());
        basePageModel.setPageTotal(page.getPages());

        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(list);
//        basePageModel.setPageTotal(pageInfo.getPages());
//        basePageModel.setRecords((int)pageInfo.getTotal());
        return basePageModel;
    }

    @RequestMapping(value = "/addPage",method = RequestMethod.GET)
    public String add(Model model){
        getCardInfoExtra(model);
        Map<String,String> map = new HashMap<>();
        map.put("taunt","嘲讽");
        map.put("freeze","冻结");
        map.put("windfury","风怒");
        map.put("battlecry","战吼");
        map.put("stealth","潜行");
        map.put("combo","连击");
        map.put("aura","光环");
        map.put("charge","冲锋");
        map.put("grantCharge","过载");
        map.put("spellPower","法强");
        map.put("silence","沉默");
        map.put("enrage","激怒");
        map.put("divineShield","圣盾");
        map.put("deathRattle","亡语");
        map.put("secret","奥秘");
        map.put("inspire","激励");
        map.put("mission","任务");
        map.put("lifeSteal","吸血");

        model.addAttribute("cardProperty",map);
        return "card/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Boolean actionAdd(Card card) {
        if(cardService.add(card)){
            CardProperty cardProperty = card.getCardProperty();
            cardProperty.setCardId(card.getId());
            if(cardPropertyService.add(cardProperty)){
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/editPage/{cardId}",method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Integer cardId){
        getCardInfoExtra(model);

        Card card = new Card();
        card.setId(cardId);
        card = cardService.get(card);
        if(card != null){
            model.addAttribute("card",card);
            CardProperty cardProperty = new CardProperty();
            cardProperty.setCardId(cardId);
            cardProperty = cardPropertyService.get(cardProperty);
            model.addAttribute("cardProperty",getCheckList(cardProperty));
        }
        return "card/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Boolean editAction(Card card) {
        if(cardService.update(card) > 0){
            CardProperty cardPropertyTemp = new CardProperty();
            cardPropertyTemp.setCardId(card.getId());
            cardPropertyTemp = cardPropertyService.get(cardPropertyTemp);
            CardProperty cardProperty = card.getCardProperty();
            cardProperty.setId(cardPropertyTemp.getId());
            if(cardPropertyService.update(cardProperty)){
                return true;
            }
        }
        return false;
    }

    private List<CheckItem> getCheckList(CardProperty cardProperty){
        List<CheckItem> list = new ArrayList<>();
        list.add(new CheckItem("taunt","嘲讽",cardProperty.getTaunt()));
        list.add(new CheckItem("freeze","冻结",cardProperty.getFreeze()));
        list.add(new CheckItem("windfury","风怒",cardProperty.getWindfury()));
        list.add(new CheckItem("battlecry","战吼",cardProperty.getBattlecry()));
        list.add(new CheckItem("stealth","潜行",cardProperty.getStealth()));
        list.add(new CheckItem("combo","连击",cardProperty.getCombo()));
        list.add(new CheckItem("aura","光环",cardProperty.getAura()));
        list.add(new CheckItem("charge","冲锋",cardProperty.getCharge()));
        list.add(new CheckItem("grantCharge","过载",cardProperty.getGrantCharge()));
        list.add(new CheckItem("spellpower","法强",cardProperty.getSpellpower()));
        list.add(new CheckItem("silence","沉默",cardProperty.getSilence()));
        list.add(new CheckItem("enrage","激怒",cardProperty.getEnrage()));
        list.add(new CheckItem("divineShield","圣盾",cardProperty.getDivineShield()));
        list.add(new CheckItem("deathrattle","亡语",cardProperty.getDeathrattle()));
        list.add(new CheckItem("secret","奥秘",cardProperty.getSecret()));
        list.add(new CheckItem("inspire","激励",cardProperty.getInspire()));
        list.add(new CheckItem("mission","任务",cardProperty.getMission()));
        list.add(new CheckItem("lifesteal","吸血",cardProperty.getLifesteal()));
        return  list;
    }

    private void getCardInfoExtra(Model model){
        model.addAttribute("cardTypeList",cardTypeService.getList(new CardType()));
        model.addAttribute("cardKindList",cardKindService.getList(new CardKind()));
        model.addAttribute("cardOccupationList",cardOccupationService.getList(new CardOccupation()));
        model.addAttribute("cardRarityList",cardRarityService.getList(new CardRarity()));
        model.addAttribute("cardSetList",cardSetService.getList(new CardSet()));
    }

    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public String detail(@PathVariable Integer id,Model model){
        Card card = new Card();
        card.setId(id);
        List<Map<String,Object>> list = cardService.getListDisplay(card);
        Map<String,Object> map = new HashMap<>();
        if(list != null && list.size() >0){
            map = list.get(0);
            model.addAttribute("card",map);
            CardProperty cardProperty = new CardProperty();
            cardProperty.setCardId(id);
            cardProperty = cardPropertyService.get(cardProperty);

            Map<String,Boolean> map_property = new HashMap<>();
            map_property.put("嘲讽",cardProperty.getTaunt());
            map_property.put("冻结",cardProperty.getFreeze());
            map_property.put("风怒",cardProperty.getWindfury());
            map_property.put("战吼",cardProperty.getBattlecry());
            map_property.put("潜行",cardProperty.getStealth());
            map_property.put("连击",cardProperty.getCombo());
            map_property.put("光环",cardProperty.getAura());
            map_property.put("冲锋",cardProperty.getCharge());
            map_property.put("过载",cardProperty.getGrantCharge());
            map_property.put("法强",cardProperty.getSpellpower());
            map_property.put("沉默",cardProperty.getSilence());
            map_property.put("激怒",cardProperty.getEnrage());
            map_property.put("圣盾",cardProperty.getDivineShield());
            map_property.put("亡语",cardProperty.getDeathrattle());
            map_property.put("奥秘",cardProperty.getSecret());
            map_property.put("激励",cardProperty.getInspire());
            map_property.put("任务",cardProperty.getMission());
            map_property.put("吸血",cardProperty.getLifesteal());
            model.addAttribute("cardProperty",map_property);
        }
        return "card/detail";
    }

    @RequestMapping(value = "/queryListByaPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取卡牌", notes = "获取卡牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", required = true, dataType = "int", paramType = "query",defaultValue = "1",readOnly = true),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "query",defaultValue = "10",readOnly = true)
    })
    public Object queryListByPage(Integer pageNum, Integer pageSize) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",1);
        jsonObject.put("msg","操作成功");

        Page<?> page = PageHelper.startPage(pageNum, pageSize);
        Card card = new Card();
        List<Map<String,Object>> list = cardService.getListDisplaySwagger(card);
        List<JSONObject> list_data = new ArrayList<>();
        for (Map<String,Object> item: list) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("卡牌名称",item.get("paramName"));
            jsonObject1.put("类型",item.get("typeDisplay"));
            jsonObject1.put("职业",item.get("occupationDisplay"));
            jsonObject1.put("稀有度",item.get("rarityDisplay"));
            jsonObject1.put("种类",item.get("kindDisplay"));
            jsonObject1.put("所属集合",item.get("setDisplay"));
            jsonObject1.put("费用",item.get("Cost"));
            jsonObject1.put("攻击力",item.get("Atk"));
            jsonObject1.put("生命值",item.get("Health"));
            jsonObject1.put("分解获得的尘",item.get("Dust_Decompose"));
            jsonObject1.put("金色分解获得的尘",item.get("Dust_Decompose_Golden"));
            jsonObject1.put("合成需要的尘",item.get("Dust_Synthesis"));
            jsonObject1.put("金色合成需要的尘",item.get("Dust_Synthesis_Golden"));

            List<String> list_property = new ArrayList<>();
            if(Boolean.parseBoolean(item.get("Taunt").toString())){
                list_property.add("嘲讽");
            }
            if(Boolean.parseBoolean(item.get("Freeze").toString())){
                list_property.add("冻结");
            }
            if(Boolean.parseBoolean(item.get("Windfury").toString())){
                list_property.add("风怒");
            }
            if(Boolean.parseBoolean(item.get("Battlecry").toString())){
                list_property.add("战吼");
            }
            if(Boolean.parseBoolean(item.get("Stealth").toString())){
                list_property.add("潜行");
            }
            if(Boolean.parseBoolean(item.get("Combo").toString())){
                list_property.add("连击");
            }
            if(Boolean.parseBoolean(item.get("Aura").toString())){
                list_property.add("光环");
            }
            if(Boolean.parseBoolean(item.get("Charge").toString())){
                list_property.add("冲锋");
            }
            if(Boolean.parseBoolean(item.get("Grant_charge").toString())){
                list_property.add("过载");
            }
            if(Boolean.parseBoolean(item.get("Spellpower").toString())){
                list_property.add("法强");
            }
            if(Boolean.parseBoolean(item.get("Silence").toString())){
                list_property.add("沉默");
            }
            if(Boolean.parseBoolean(item.get("Enrage").toString())){
                list_property.add("激怒");
            }
            if(Boolean.parseBoolean(item.get("Divine_shield").toString())){
                list_property.add("圣盾");
            }
            if(Boolean.parseBoolean(item.get("Deathrattle").toString())){
                list_property.add("亡语");
            }
            if(Boolean.parseBoolean(item.get("Secret").toString())){
                list_property.add("奥秘");
            }
            if(Boolean.parseBoolean(item.get("Inspire").toString())){
                list_property.add("激励");
            }
            if(Boolean.parseBoolean(item.get("Mission").toString())){
                list_property.add("任务");
            }
            if(Boolean.parseBoolean(item.get("Lifesteal").toString())){
                list_property.add("吸血");
            }
            jsonObject1.put("属性",list_property.toString());
            jsonObject1.put("图片链接","http://localhost:8181/" + item.get("id") + ".png");
            list_data.add(jsonObject1);
        }

        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(list);
        jsonObject.put("pageSize",pageSize);
        jsonObject.put("pageTotal",pageInfo.getPages());
        jsonObject.put("dataTotal",pageInfo.getTotal());
        jsonObject.put("data",list_data);
        return jsonObject;
    }
}
