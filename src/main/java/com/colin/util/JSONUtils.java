package com.colin.util;


import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
import java.util.ArrayList;

public class JSONUtils {
    public static void main(String[] args) {
        // 读取nameID.txt文件中的NAMEID字段（key）对应值（value）并存储
        ArrayList<String> list = new ArrayList<String>();
        BufferedReader brname;
        try {
            brname = new BufferedReader(new FileReader("src/main/resources/nameID.txt"));// 读取NAMEID对应值
            String sname = null;
            while ((sname = brname.readLine()) != null) {
                //System.out.println(sname);
                list.add(sname);// 将对应value添加到链表存储
            }
            brname.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // 读取原始json文件并进行操作和输出
        try {
            BufferedReader br = new BufferedReader(new FileReader(
                    "src/main/resources/HK_geo.json"));// 读取原始json文件
            BufferedWriter bw = new BufferedWriter(new FileWriter(
                    "src/main/resources/HK_new.json"));// 输出新的json文件
            String s = "", ws = null;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
                try {
                    JSONObject dataJson = new JSONObject(s);// 创建一个包含原始json串的json对象
                    JSONArray features = dataJson.getJSONArray("features");// 找到features json数组
                    for (int i = 0; i < features.length(); i++) {
                        JSONObject info = features.getJSONObject(i);// 获取features数组的第i个json对象
                        JSONObject properties = info.getJSONObject("properties");// 找到properties的json对象
                        String name = properties.getString("name");// 读取properties对象里的name字段值
                        properties.put("NAMEID", list.get(i));// 添加NAMEID字段
                        properties.put("ISO", "123");//替换ISO字段的值
                        properties.remove("name");// 删除name字段
                    }
                    ws = dataJson.toString();
                    System.out.println(ws);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            bw.write(ws);
            bw.newLine();

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
