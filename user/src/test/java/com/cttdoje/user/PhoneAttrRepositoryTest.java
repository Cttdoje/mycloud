package com.cttdoje.user;

import com.alibaba.fastjson.JSONObject;
import com.cttdoje.user.dao.PhoneAttrRepository;
import com.cttdoje.user.entity.PhoneAttrEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SpringBootTest
public class PhoneAttrRepositoryTest {

    @Autowired
    private PhoneAttrRepository phoneAttrRepository;

    @Test
    public void test1() {
        PhoneAttrEntity phoneAttrEntity = new PhoneAttrEntity();
        phoneAttrEntity.setPrefix(154);
        phoneAttrEntity.setCity("福州22");
        phoneAttrEntity.setCreateDate(new Timestamp(LocalDateTime.now().toInstant(ZoneOffset.of("+0")).toEpochMilli()));
        phoneAttrRepository.save(phoneAttrEntity);


       /*String filePath = "D:\\project\\twobi" + "\\phoneAttr.txt";
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
            String s;
            while ((s = br.readLine()) != null) {
                if (s.startsWith("prefix")) {
                    continue;
                }
                String cut = "\\s+";
                String[] info = s.trim().split(cut);
                String[] prefix = new String[]{"190","191","193","194","195","196","197","198","199"};
                if (info.length == 8) {
                    for(String str:prefix){
                        String oldPrefix = info[0];
                        String newPhone = info[1].replace(oldPrefix,str);
                        PhoneAttrEntity phone = new PhoneAttrEntity(str, newPhone, info[2], info[3], "电信", info[5], info[6], info[7]);
                        phone.setCreateDate(new Timestamp(LocalDateTime.now().toInstant(ZoneOffset.of("+0")).toEpochMilli()));
                        phoneAttrRepository.save(phone);


                        phone.setIsp("联通");
                        phoneAttrRepository.save(phone);

                        phone.setIsp("移动");
                        phoneAttrRepository.save(phone);
                    }

                }
            }
        } catch (Exception e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }*/
    }


    @Test
    public void insert() {
        PhoneAttrEntity phoneAttrEntity = new PhoneAttrEntity();
        phoneAttrEntity.setPrefix(154);
        phoneAttrEntity.setCity("福州22");
        phoneAttrEntity.setCreateDate(new Timestamp(LocalDateTime.now().toInstant(ZoneOffset.of("+0")).toEpochMilli()));
        phoneAttrRepository.save(phoneAttrEntity);
    }

    @Test
    public void query() {
        List<PhoneAttrEntity> list = phoneAttrRepository.findAll();
        System.out.println(JSONObject.toJSONString(list, true));
    }

    @Test
    public void update() {
        // System.out.println(phoneAttrRepository.count());
        List list = phoneAttrRepository.findByProvinceAndPrefix("福建", 155);
        System.out.println(JSONObject.toJSONString(list, true));

        list = phoneAttrRepository.getPhoneAttrEntity("联通", 154);
        System.out.println(JSONObject.toJSONString(list, true));
    }

    @Test
    public void queryPhoneAttr() {
        PhoneAttrEntity entity = phoneAttrRepository.findPhoneAttrEntityByPrefixAndProvince(155, "福建");
        System.out.println(JSONObject.toJSONString(entity));
    }

    public static void main(String[] args) {
        Map<Key, Integer> map = new HashMap<>();
        Key key = new Key("a");
        map.put(key, 1);
        key.setA("b");
        System.out.println(map.get(key));
    }

    static class Key {
        private String a;

        public Key(String a) {
            this.a = a;
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return a.equals(key.a);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }
}


