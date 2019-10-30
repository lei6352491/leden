package com.zhouyi.business.controller;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.core.model.LedenCollectPerson;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.model.SearchParam;
import com.zhouyi.business.model.SearchResponseData;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/person")
@Api(hidden = true)
public class SearchController {

    @Autowired
    private RestHighLevelClient client;

    /*@Autowired
    private RestClient restClient;*/

    /**
     *根据条件查询索引库
     * */
    @RequestMapping(value = "/person/_search")
    public Response selectSearchPerson(@RequestBody SearchParam searchParam) throws IOException{
        //初始化分页参数
        initializationPageParam(searchParam);
        //创建查询对象
        SearchRequest searchRequest = new SearchRequest("person");
        searchRequest.types("person");
        //创建源数据对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //设置分页条件
        int from = (searchParam.getPage() - 1) * searchParam.getSize();
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(searchParam.getSize());
        //source源字段过虑(默认全显示)
        searchSourceBuilder.fetchSource(null,new String[]{"@version","@timestamp"});
        //searchSourceBuilder.fetchSource(true);
        //设置查询的方式
        MultiMatchQueryBuilder multiMatchQueryBuilder = null;
        if (StringUtils.isEmpty(searchParam.getSearchParam())){
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        }else {
            //设置查询的域
            String[] arr = {"ryjcxxcjbh","jzrybh","xm","xmhypy","grsfdm","cym","wwxm","bmch","csdxz",
            "hjdxz","xzdxz","cjrxm","cjdwmc","equipment_code","annex"};
            multiMatchQueryBuilder = QueryBuilders.multiMatchQuery
                    (searchParam.getSearchParam(), arr).operator(Operator.OR).field
                    ("xm",10).field("xmhypy",5).field("cjrxm",10).field("cjdwmc",10);
            searchSourceBuilder.query(multiMatchQueryBuilder);
        }

        //布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(searchSourceBuilder.query());

        //过虑
        //过滤性别
        if (StringUtils.isNotEmpty(searchParam.getXbdm())){
            boolQueryBuilder.filter(QueryBuilders.termQuery("xbdm",searchParam.getXbdm()));
        }
        //过滤出生时间范围
        if (searchParam.getCsrqStart() != null){
            if (searchParam.getCsrqEnd() == null){
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("csrq")
                        .gte(searchParam.getCsrqStart()).lte(new Date()));
            }else {
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("csrq")
                        .gte(searchParam.getCsrqStart()).lte(searchParam.getCsrqEnd()));
            }
        }
        if (searchParam.getCsrqStart() == null && searchParam.getCsrqEnd() != null){
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("csrq")
                    .gte(new Date(0)).lte(searchParam.getCsrqEnd()));
        }
        //过滤采集时间范围
        if (searchParam.getCsrqStart() != null){
            if (searchParam.getCsrqEnd() == null){
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("cjsj")
                        .gte(searchParam.getCsrqStart()).lte(new Date()));
            }else {
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("cjsj")
                        .gte(searchParam.getCsrqStart()).lte(searchParam.getCsrqEnd()));
            }
        }
        if (searchParam.getCsrqStart() == null && searchParam.getCsrqEnd() != null){
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("cjsj")
                    .gte(new Date(0)).lte(searchParam.getCsrqEnd()));
        }
        //排序
        searchSourceBuilder.sort(new FieldSortBuilder("cjsj").order(SortOrder.DESC));

        //高亮设置
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<span style=\"color:#BB362E\">");
        highlightBuilder.postTags("</span>");
        // 设置高亮字段
        highlightBuilder.fields().add(new HighlightBuilder.Field("ryjcxxcjbh"));
        highlightBuilder.fields().add(new HighlightBuilder.Field("jzrybh"));
        highlightBuilder.fields().add(new HighlightBuilder.Field("xm"));
        highlightBuilder.fields().add(new HighlightBuilder.Field("xmhypy"));
        highlightBuilder.fields().add(new HighlightBuilder.Field("gmsfhm"));
        highlightBuilder.fields().add(new HighlightBuilder.Field("cjrxm"));
        highlightBuilder.fields().add(new HighlightBuilder.Field("csdxz"));
        highlightBuilder.fields().add(new HighlightBuilder.Field("cjdwmc"));
        highlightBuilder.fields().add(new HighlightBuilder.Field("hjdxz"));
        highlightBuilder.fields().add(new HighlightBuilder.Field("xzdxz"));
        //把高亮设置添加到源数据中
        searchSourceBuilder.highlighter(highlightBuilder);

        //把布尔查询方式添加到源查询对象中
        searchSourceBuilder.query(boolQueryBuilder);
        //把源数据对象添加到查询对象中
        searchRequest.source(searchSourceBuilder);

        //查询
        SearchResponse searchResponse = client.search(searchRequest);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        long totalHits = hits.getTotalHits();
        List<LedenCollectPerson> list = new ArrayList<>();

        for (SearchHit hit : searchHits){
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            String string = JSON.toJSONString(sourceAsMap);
            LedenCollectPerson ledenCollectPerson =
                    JSON.parseObject(string, com.zhouyi.business.core.model.LedenCollectPerson.class);
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (highlightFields != null){
                HighlightField ryjcxxcjbhFiled = highlightFields.get("ryjcxxcjbh");
                HighlightField jzrybhhFiled = highlightFields.get("jzrybh");
                HighlightField xmFiled = highlightFields.get("xm");
                HighlightField xmhypyFiled = highlightFields.get("xmhypy");
                HighlightField gmsfhmFiled = highlightFields.get("gmsfhm");
                HighlightField cjrxmFiled = highlightFields.get("cjrxm");
                HighlightField csdxzFiled = highlightFields.get("csdxz");
                HighlightField cjdwmcFiled = highlightFields.get("cjdwmc");
                HighlightField hjdxzFiled = highlightFields.get("hjdxz");
                HighlightField xzdxzFiled = highlightFields.get("xzdxz");

                if (ryjcxxcjbhFiled != null){
                    Text[] fragments = ryjcxxcjbhFiled.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Text str : fragments) {
                        stringBuffer.append(str.string());
                    }
                    ledenCollectPerson.setRyjcxxcjbh(stringBuffer.toString());
                }
                if (jzrybhhFiled != null){
                    Text[] fragments = jzrybhhFiled.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Text str : fragments) {
                        stringBuffer.append(str.string());
                    }
                    ledenCollectPerson.setJzrybh(stringBuffer.toString());
                }

                if (xmFiled != null){
                    Text[] fragments = xmFiled.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Text str : fragments) {
                        stringBuffer.append(str.string());
                    }
                    ledenCollectPerson.setXm(stringBuffer.toString());
                }
                if (xmhypyFiled != null){
                    Text[] fragments = xmhypyFiled.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Text str : fragments) {
                        stringBuffer.append(str.string());
                    }
                    ledenCollectPerson.setXmhypy(stringBuffer.toString());
                }
                if (gmsfhmFiled != null){
                    Text[] fragments = gmsfhmFiled.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Text str : fragments) {
                        stringBuffer.append(str.string());
                    }
                    ledenCollectPerson.setGmsfhm(stringBuffer.toString());
                }
                if (cjrxmFiled != null){
                    Text[] fragments = cjrxmFiled.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Text str : fragments) {
                        stringBuffer.append(str.string());
                    }
                    ledenCollectPerson.setCjrxm(stringBuffer.toString());
                }
                if (csdxzFiled != null){
                    Text[] fragments = csdxzFiled.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Text str : fragments) {
                        stringBuffer.append(str.string());
                    }
                    ledenCollectPerson.setCsdxz(stringBuffer.toString());
                }
                if (cjdwmcFiled != null){
                    Text[] fragments = cjdwmcFiled.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Text str : fragments) {
                        stringBuffer.append(str.string());
                    }
                    ledenCollectPerson.setCjdwmc(stringBuffer.toString());
                }
                if (hjdxzFiled != null){
                    Text[] fragments = hjdxzFiled.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Text str : fragments) {
                        stringBuffer.append(str.string());
                    }
                    ledenCollectPerson.setHjdxz(stringBuffer.toString());
                }
                if (xzdxzFiled != null){
                    Text[] fragments = xzdxzFiled.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Text str : fragments) {
                        stringBuffer.append(str.string());
                    }
                    ledenCollectPerson.setXzdxz(stringBuffer.toString());
                }
            }
            list.add(ledenCollectPerson);
        }

        //返回查询的索引数据
        Response<Object> response = ResponseUtil.getResponseInfo(true);
        SearchResponseData searchResponseData = new SearchResponseData();
        searchResponseData.setList(list);
        searchResponseData.setTotal(totalHits);
        response.setData(searchResponseData);
        return response;
    }

    //初始化分页参数
    private SearchParam initializationPageParam(SearchParam searchParam){
        if (searchParam == null){
            searchParam = new SearchParam();
        }
        if (searchParam.getPage() == null || searchParam.getPage() < 1){
            searchParam.setPage(1);
        }
        if (searchParam.getSize() == null || searchParam.getSize() < 1){
            searchParam.setSize(20);
        }
        return searchParam;
    }

    //格式化含时区的时间对象
    /*private Date parseDate(String dateString){
        if (StringUtils.isNotEmpty(dateString)){
            dateString = dateString.replace("Z", " UTC");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            Date date = null;
            try {
                date = format.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }
        return null;
    }*/

    //滤空
    /*private byte[] filteredAirByte(String string){
        if (StringUtils.isNotEmpty(string))
            return string.getBytes();
        else
            return null;
    }*/

}
