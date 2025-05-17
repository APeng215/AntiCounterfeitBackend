package com.apeng.anticounterfeitbackend.entity;

import com.alibaba.fastjson2.JSONObject;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Embeddable
@Data
@NoArgsConstructor
public class Location {

    private String nation = "中国", province = "河南", city = "郑州市", district;
    private Double lat = 34.7599;
    private Double lng = 113.6459;

    /**
     * Populate the instance with response object from API
     * @param responseObject Example: {
     *    "code":"0000",
     *    "msg":"交易成功",
     *    "traceId":"51509326bbda48569482022c294803b7.136.17458079496060271",
     *    "appdata":{
     *       "nation":null,
     *       "province":null,
     *       "city":null,
     *       "district":null,
     *       "lat":null,
     *       "lng":null
     *    }
     * }
     */
    public void populate(JSONObject responseObject) {
        JSONObject appdata = responseObject.getJSONObject("appdata");
        nation = appdata.getString("nation");
        province = appdata.getString("province");
        city = appdata.getString("city");
        district = appdata.getString("district");
        lat = appdata.getDouble("lat");
        lng = appdata.getDouble("lng");
    }

    public static Location ofJsonObject(JSONObject responseObject) {
        Location result = new Location();
        result.populate(responseObject);
        if (result.lat == null) {
            result.lat = 34.7599;
            result.lng = 113.6459;
        }
        return result;
    }

}
