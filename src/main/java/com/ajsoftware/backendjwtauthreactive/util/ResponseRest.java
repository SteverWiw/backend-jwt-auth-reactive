package com.ajsoftware.backendjwtauthreactive.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseRest<T> {
    private final ArrayList<HashMap<String,String >> metaData = new ArrayList<>();
    private List<T> objectList;

    public void setMetaData(String type,int code,String date) {
        HashMap<String,String> map = new HashMap<>();
        map.put("type",type);
        map.put("code", String.valueOf(code));
        map.put("date",date);
        metaData.add(map);
    }

    public void setObject(List<T> objectList) {
        this.objectList = objectList;
    }
}
