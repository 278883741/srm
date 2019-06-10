package com.imooc.model;

import java.util.Date;
import javax.persistence.*;

public class Card {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "param_name")
    private String paramName;

    @Column(name = "EName")
    private String ename;

    /**
     * 卡牌类型,关联表HS_CardType
     */
    @Column(name = "Type")
    private Integer type;

    /**
     * 职业,关联表HS_CardOccupation
     */
    @Column(name = "Occupation")
    private Integer occupation;

    /**
     * 稀有度,关联表HS_CardRarity
     */
    @Column(name = "Rarity")
    private Integer rarity;

    @Column(name = "Cost")
    private Integer cost;

    @Column(name = "Atk")
    private Integer atk;

    @Column(name = "Health")
    private Integer health;

    /**
     * 卡牌种类，关联表Card_Kind
     */
    @Column(name = "Kind")
    private Integer kind;

    @Column(name = "param_set")
    private Integer paramSet;

    @Column(name = "Description")
    private String description;

    /**
     * 分解获得的尘
     */
    @Column(name = "Dust_Decompose")
    private Integer dustDecompose;

    /**
     * 金色分解获得的尘
     */
    @Column(name = "Dust_Decompose_Golden")
    private Integer dustDecomposeGolden;

    /**
     * 合成需要的尘
     */
    @Column(name = "Dust_Synthesis")
    private Integer dustSynthesis;

    /**
     * 金色合成需要的尘
     */
    @Column(name = "Dust_Synthesis_Golden")
    private Integer dustSynthesisGolden;

    @Column(name = "IsShow")
    private Boolean isshow;

    @Column(name = "CreateDate")
    private Date createdate;

    @Column(name = "img_Path")
    private String imgPath;
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    public String getImgPath() {
        return imgPath;
    }

    @Transient
    private CardProperty cardProperty;
    public void setCardProperty(CardProperty cardProperty) {
        this.cardProperty = cardProperty;
    }
    public CardProperty getCardProperty() {
        return cardProperty;
    }
    /**
     * @return Id
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
     * @return param_name
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * @param paramName
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * @return EName
     */
    public String getEname() {
        return ename;
    }

    /**
     * @param ename
     */
    public void setEname(String ename) {
        this.ename = ename;
    }

    /**
     * 获取卡牌类型,关联表HS_CardType
     *
     * @return Type - 卡牌类型,关联表HS_CardType
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置卡牌类型,关联表HS_CardType
     *
     * @param type 卡牌类型,关联表HS_CardType
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取职业,关联表HS_CardOccupation
     *
     * @return Occupation - 职业,关联表HS_CardOccupation
     */
    public Integer getOccupation() {
        return occupation;
    }

    /**
     * 设置职业,关联表HS_CardOccupation
     *
     * @param occupation 职业,关联表HS_CardOccupation
     */
    public void setOccupation(Integer occupation) {
        this.occupation = occupation;
    }

    /**
     * 获取稀有度,关联表HS_CardRarity
     *
     * @return Rarity - 稀有度,关联表HS_CardRarity
     */
    public Integer getRarity() {
        return rarity;
    }

    /**
     * 设置稀有度,关联表HS_CardRarity
     *
     * @param rarity 稀有度,关联表HS_CardRarity
     */
    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    /**
     * @return Cost
     */
    public Integer getCost() {
        return cost;
    }

    /**
     * @param cost
     */
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    /**
     * @return Atk
     */
    public Integer getAtk() {
        return atk;
    }

    /**
     * @param atk
     */
    public void setAtk(Integer atk) {
        this.atk = atk;
    }

    /**
     * @return Health
     */
    public Integer getHealth() {
        return health;
    }

    /**
     * @param health
     */
    public void setHealth(Integer health) {
        this.health = health;
    }

    /**
     * 获取卡牌种类，关联表Card_Kind
     *
     * @return Kind - 卡牌种类，关联表Card_Kind
     */
    public Integer getKind() {
        return kind;
    }

    /**
     * 设置卡牌种类，关联表Card_Kind
     *
     * @param kind 卡牌种类，关联表Card_Kind
     */
    public void setKind(Integer kind) {
        this.kind = kind;
    }

    /**
     * @return param_set
     */
    public Integer getParamSet() {
        return paramSet;
    }

    /**
     * @param paramSet
     */
    public void setParamSet(Integer paramSet) {
        this.paramSet = paramSet;
    }

    /**
     * @return Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取分解获得的尘
     *
     * @return Dust_Decompose - 分解获得的尘
     */
    public Integer getDustDecompose() {
        return dustDecompose;
    }

    /**
     * 设置分解获得的尘
     *
     * @param dustDecompose 分解获得的尘
     */
    public void setDustDecompose(Integer dustDecompose) {
        this.dustDecompose = dustDecompose;
    }

    /**
     * 获取金色分解获得的尘
     *
     * @return Dust_Decompose_Golden - 金色分解获得的尘
     */
    public Integer getDustDecomposeGolden() {
        return dustDecomposeGolden;
    }

    /**
     * 设置金色分解获得的尘
     *
     * @param dustDecomposeGolden 金色分解获得的尘
     */
    public void setDustDecomposeGolden(Integer dustDecomposeGolden) {
        this.dustDecomposeGolden = dustDecomposeGolden;
    }

    /**
     * 获取合成需要的尘
     *
     * @return Dust_Synthesis - 合成需要的尘
     */
    public Integer getDustSynthesis() {
        return dustSynthesis;
    }

    /**
     * 设置合成需要的尘
     *
     * @param dustSynthesis 合成需要的尘
     */
    public void setDustSynthesis(Integer dustSynthesis) {
        this.dustSynthesis = dustSynthesis;
    }

    /**
     * 获取金色合成需要的尘
     *
     * @return Dust_Synthesis_Golden - 金色合成需要的尘
     */
    public Integer getDustSynthesisGolden() {
        return dustSynthesisGolden;
    }

    /**
     * 设置金色合成需要的尘
     *
     * @param dustSynthesisGolden 金色合成需要的尘
     */
    public void setDustSynthesisGolden(Integer dustSynthesisGolden) {
        this.dustSynthesisGolden = dustSynthesisGolden;
    }

    /**
     * @return IsShow
     */
    public Boolean getIsshow() {
        return isshow;
    }

    /**
     * @param isshow
     */
    public void setIsshow(Boolean isshow) {
        this.isshow = isshow;
    }

    /**
     * @return CreateDate
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * @param createdate
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}