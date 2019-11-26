package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author 杜承旭
 * @ClassNmae: YearCollectData
 * @Description: TODO
 * @date 2019/11/22 13:45
 * @Version 1.0
 **/
@Data
@ToString
public class YearCollectData {

    private Integer january;
    private Integer february;
    private Integer march;
    private Integer april;
    private Integer may;
    private Integer june;
    private Integer july ;
    private Integer august ;
    private Integer september;
    private Integer october;
    private Integer november;
    private Integer december;

    public YearCollectData() {
    }

    public YearCollectData(Integer january, Integer february, Integer march, Integer april, Integer may, Integer june, Integer july, Integer august, Integer september, Integer october, Integer november, Integer december) {
        this.january = january;
        this.february = february;
        this.march = march;
        this.april = april;
        this.may = may;
        this.june = june;
        this.july = july;
        this.august = august;
        this.september = september;
        this.october = october;
        this.november = november;
        this.december = december;
    }
}
