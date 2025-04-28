package com.apeng.anticounterfeitbackend.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.apeng.anticounterfeitbackend.dto.ProductRequest;
import com.apeng.anticounterfeitbackend.dto.ProductResponse;
import com.apeng.anticounterfeitbackend.dto.ValidationRequest;
import com.apeng.anticounterfeitbackend.entity.Location;
import com.apeng.anticounterfeitbackend.entity.Product;
import com.apeng.anticounterfeitbackend.entity.QueryInfo;
import com.apeng.anticounterfeitbackend.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    private final static String APP_CODE = "bb92ecf8b1a942d99ecfab8a2148025f";

    @Autowired
    private ProductService productService;

    @PostMapping
    public List<Product> add(@RequestBody ProductRequest productRequest) {
        validateEmpty(productRequest);
        return productService.add(productRequest);
    }

    private static void validateEmpty(ProductRequest productRequest) {
        if (productRequest.hasNullField()) {
            throw new IllegalArgumentException("Product request has empty field!");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return productService.getAll().stream().map(ProductResponse::new).toList();
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @PatchMapping("/{id}")
    public Product bindColors(@PathVariable Long id, @RequestBody List<String> colorsInHex) {
        List<Color> colors = convert2ColorObjects(colorsInHex);
        return productService.bindColors(id, colors);
    }

    @PostMapping("/validate")
    public ProductResponse validate(@RequestBody ValidationRequest validationRequest, HttpServletRequest request) {
        QueryInfo queryInfo = queryIpAddress(extractClientIp(request));
        System.out.println(queryInfo);
        return new ProductResponse(productService.validate(validationRequest.getUuid(), validationRequest.getSignature(), queryInfo));
    }

    private static List<Color> convert2ColorObjects(List<String> colorsInHex) {
        return colorsInHex.stream().map(colorHex -> new Color(hexToPackedRgb(colorHex))).toList();
    }

    public static int hexToPackedRgb(String hexColor) {
        // Remove '#' if present
        String hex = hexColor.startsWith("#") ? hexColor.substring(1) : hexColor;

        // Validate length (6 characters)
        if (hex.length() != 6) {
            throw new IllegalArgumentException("Invalid hex color format. Use #RRGGBB or RRGGBB.");
        }

        // Parse hex to RGB components
        int r = Integer.parseInt(hex.substring(0, 2), 16); // Red (bits 16-23)
        int g = Integer.parseInt(hex.substring(2, 4), 16); // Green (bits 8-15)
        int b = Integer.parseInt(hex.substring(4, 6), 16); // Blue (bits 0-7)

        // Combine into packed integer (0xRRGGBB)
        return (r << 16) | (g << 8) | b;
    }

    private String extractClientIp(HttpServletRequest request) {
        // Standard header used by proxies/load balancers
        String xff = request.getHeader("X-Forwarded-For");
        if (xff != null && !xff.isEmpty()) {
            // X-Forwarded-For can be a comma-list: client, proxy1, proxy2…
            return xff.split(",")[0].trim();
        }
        // Fallback to the direct remote address
        return request.getRemoteAddr();
    }

    private QueryInfo queryIpAddress(String ip) {
//        String host = "https://lxipaddr.market.alicloudapi.com";
//        String path = "/iplocaltion/getName";
//        String method = "GET";
//        String appcode = "bb92ecf8b1a942d99ecfab8a2148025f";
//        Map<String, String> headers = new HashMap<String, String>();
//        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//        headers.put("Authorization", "APPCODE " + appcode);
//        Map<String, String> querys = new HashMap<String, String>();
//        querys.put("ip", ip);

        OkHttpClient client = new OkHttpClient();

        HttpUrl url = HttpUrl.parse("https://lxipaddr.market.alicloudapi.com/iplocaltion/getName")
                .newBuilder()
                .addQueryParameter("ip", ip)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization", "APPCODE " + APP_CODE)
                .build();

        return requestAndParse(client, request);
    }

    @NotNull
    private static QueryInfo requestAndParse(OkHttpClient client, Request request) {
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            } else {
                JSONObject responseJsonObject = JSON.parseObject(response.body().bytes());
                QueryInfo queryInfo = new QueryInfo();
                populateQueryInfo(queryInfo, responseJsonObject);
                return queryInfo;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void populateQueryInfo(QueryInfo queryInfo, JSONObject responseJsonObject) {
        queryInfo.setQueryTime(Timestamp.from(Instant.now()));
        queryInfo.setLocation(Location.ofJsonObject(responseJsonObject));
    }

}
