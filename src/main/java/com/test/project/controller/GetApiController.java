package com.test.project.controller;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Iterator;


@RestController // 여기는 컨트롤러라고 알려주는 @RestController 어노테이션 사용
@RequestMapping("/api") // 여기로 들어올 path를 지정할 @RequestMapping 어노테이션 사용 localhost:8080/api
public class GetApiController {

    @RequestMapping(method = RequestMethod.GET, path = "/getRequest")   // localhost:8080/api/getRequest
    public String getRequest() {
        String url = "https://www.naver.com/";    //크롤링할 url지정
        Document doc = null;        //Document에는 페이지의 전체 소스가 저장된다

        try {

            doc = Jsoup.connect(url).get();

        } catch (IOException e) {

            e.printStackTrace();

        }

        //select를 이용하여 원하는 태그를 선택한다. select는 원하는 값을 가져오기 위한 중요한 기능이다.
        //                               ==>원하는 값들이 들어있는 덩어리를 가져온다
        Elements element = doc.select("ul.list_nav li");

        Iterator<Element> ie1 = element.select("a.nav").iterator();
        String str = "";
        while (ie1.hasNext()) {
            str += ie1.next().text()+ ", ";
        }

        return "{\"result\" : \"배건호\", \"value\" : "+str +"}";
    }
}