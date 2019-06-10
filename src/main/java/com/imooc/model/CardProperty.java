package com.imooc.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "card_property")
public class CardProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色ID
     */
    @Column(name = "card_id")
    private Integer cardId;

    /**
     * 嘲讽
     */
    @Column(name = "Taunt")
    private Boolean taunt;

    /**
     * 冻结
     */
    @Column(name = "Freeze")
    private Boolean freeze;

    /**
     * 风怒
     */
    @Column(name = "Windfury")
    private Boolean windfury;

    /**
     * 战吼
     */
    @Column(name = "Battlecry")
    private Boolean battlecry;

    /**
     * 潜行
     */
    @Column(name = "Stealth")
    private Boolean stealth;

    /**
     * 连击
     */
    @Column(name = "Combo")
    private Boolean combo;

    /**
     * 光环
     */
    @Column(name = "Aura")
    private Boolean aura;

    /**
     * 冲锋
     */
    @Column(name = "Charge")
    private Boolean charge;

    /**
     * 过载
     */
    @Column(name = "Grant_charge")
    private Boolean grantCharge;

    /**
     * 法强
     */
    @Column(name = "Spellpower")
    private Boolean spellpower;

    /**
     * 沉默
     */
    @Column(name = "Silence")
    private Boolean silence;

    /**
     * 激怒
     */
    @Column(name = "Enrage")
    private Boolean enrage;

    /**
     * 圣盾
     */
    @Column(name = "Divine_shield")
    private Boolean divineShield;

    /**
     * 亡语
     */
    @Column(name = "Deathrattle")
    private Boolean deathrattle;

    /**
     * 奥秘
     */
    @Column(name = "Secret")
    private Boolean secret;

    /**
     * 激励
     */
    @Column(name = "Inspire")
    private Boolean inspire;

    /**
     * 任务
     */
    @Column(name = "Mission")
    private Boolean mission;

    /**
     * 吸血
     */
    @Column(name = "Lifesteal")
    private Boolean lifesteal;

    /**
     * 创建人
     */
    private String operator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色ID
     *
     * @return card_id - 角色ID
     */
    public Integer getCardId() {
        return cardId;
    }

    /**
     * 设置角色ID
     *
     * @param cardId 角色ID
     */
    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    /**
     * 获取嘲讽
     *
     * @return Taunt - 嘲讽
     */
    public Boolean getTaunt() {
        return taunt;
    }

    /**
     * 设置嘲讽
     *
     * @param taunt 嘲讽
     */
    public void setTaunt(Boolean taunt) {
        this.taunt = taunt;
    }

    /**
     * 获取冻结
     *
     * @return Freeze - 冻结
     */
    public Boolean getFreeze() {
        return freeze;
    }

    /**
     * 设置冻结
     *
     * @param freeze 冻结
     */
    public void setFreeze(Boolean freeze) {
        this.freeze = freeze;
    }

    /**
     * 获取风怒
     *
     * @return Windfury - 风怒
     */
    public Boolean getWindfury() {
        return windfury;
    }

    /**
     * 设置风怒
     *
     * @param windfury 风怒
     */
    public void setWindfury(Boolean windfury) {
        this.windfury = windfury;
    }

    /**
     * 获取战吼
     *
     * @return Battlecry - 战吼
     */
    public Boolean getBattlecry() {
        return battlecry;
    }

    /**
     * 设置战吼
     *
     * @param battlecry 战吼
     */
    public void setBattlecry(Boolean battlecry) {
        this.battlecry = battlecry;
    }

    /**
     * 获取潜行
     *
     * @return Stealth - 潜行
     */
    public Boolean getStealth() {
        return stealth;
    }

    /**
     * 设置潜行
     *
     * @param stealth 潜行
     */
    public void setStealth(Boolean stealth) {
        this.stealth = stealth;
    }

    /**
     * 获取连击
     *
     * @return Combo - 连击
     */
    public Boolean getCombo() {
        return combo;
    }

    /**
     * 设置连击
     *
     * @param combo 连击
     */
    public void setCombo(Boolean combo) {
        this.combo = combo;
    }

    /**
     * 获取光环
     *
     * @return Aura - 光环
     */
    public Boolean getAura() {
        return aura;
    }

    /**
     * 设置光环
     *
     * @param aura 光环
     */
    public void setAura(Boolean aura) {
        this.aura = aura;
    }

    /**
     * 获取冲锋
     *
     * @return Charge - 冲锋
     */
    public Boolean getCharge() {
        return charge;
    }

    /**
     * 设置冲锋
     *
     * @param charge 冲锋
     */
    public void setCharge(Boolean charge) {
        this.charge = charge;
    }

    /**
     * 获取过载
     *
     * @return Grant_charge - 过载
     */
    public Boolean getGrantCharge() {
        return grantCharge;
    }

    /**
     * 设置过载
     *
     * @param grantCharge 过载
     */
    public void setGrantCharge(Boolean grantCharge) {
        this.grantCharge = grantCharge;
    }

    /**
     * 获取法强
     *
     * @return Spellpower - 法强
     */
    public Boolean getSpellpower() {
        return spellpower;
    }

    /**
     * 设置法强
     *
     * @param spellpower 法强
     */
    public void setSpellpower(Boolean spellpower) {
        this.spellpower = spellpower;
    }

    /**
     * 获取沉默
     *
     * @return Silence - 沉默
     */
    public Boolean getSilence() {
        return silence;
    }

    /**
     * 设置沉默
     *
     * @param silence 沉默
     */
    public void setSilence(Boolean silence) {
        this.silence = silence;
    }

    /**
     * 获取激怒
     *
     * @return Enrage - 激怒
     */
    public Boolean getEnrage() {
        return enrage;
    }

    /**
     * 设置激怒
     *
     * @param enrage 激怒
     */
    public void setEnrage(Boolean enrage) {
        this.enrage = enrage;
    }

    /**
     * 获取圣盾
     *
     * @return Divine_shield - 圣盾
     */
    public Boolean getDivineShield() {
        return divineShield;
    }

    /**
     * 设置圣盾
     *
     * @param divineShield 圣盾
     */
    public void setDivineShield(Boolean divineShield) {
        this.divineShield = divineShield;
    }

    /**
     * 获取亡语
     *
     * @return Deathrattle - 亡语
     */
    public Boolean getDeathrattle() {
        return deathrattle;
    }

    /**
     * 设置亡语
     *
     * @param deathrattle 亡语
     */
    public void setDeathrattle(Boolean deathrattle) {
        this.deathrattle = deathrattle;
    }

    /**
     * 获取奥秘
     *
     * @return Secret - 奥秘
     */
    public Boolean getSecret() {
        return secret;
    }

    /**
     * 设置奥秘
     *
     * @param secret 奥秘
     */
    public void setSecret(Boolean secret) {
        this.secret = secret;
    }

    /**
     * 获取激励
     *
     * @return Inspire - 激励
     */
    public Boolean getInspire() {
        return inspire;
    }

    /**
     * 设置激励
     *
     * @param inspire 激励
     */
    public void setInspire(Boolean inspire) {
        this.inspire = inspire;
    }

    /**
     * 获取任务
     *
     * @return Mission - 任务
     */
    public Boolean getMission() {
        return mission;
    }

    /**
     * 设置任务
     *
     * @param mission 任务
     */
    public void setMission(Boolean mission) {
        this.mission = mission;
    }

    /**
     * 获取吸血
     *
     * @return Lifesteal - 吸血
     */
    public Boolean getLifesteal() {
        return lifesteal;
    }

    /**
     * 设置吸血
     *
     * @param lifesteal 吸血
     */
    public void setLifesteal(Boolean lifesteal) {
        this.lifesteal = lifesteal;
    }

    /**
     * 获取创建人
     *
     * @return operator - 创建人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置创建人
     *
     * @param operator 创建人
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}