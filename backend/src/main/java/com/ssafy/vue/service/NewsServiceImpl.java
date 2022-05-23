package com.ssafy.vue.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.NewsDto;

@Service
public class NewsServiceImpl implements NewsService {

	// 네이버 부동산 뉴스
	private static String NEWS_DATAS_URL = "https://land.naver.com/news/headline.naver?bss_ymd=20220523";

	@Override
	@PostConstruct // 즉시 실행
	public List<NewsDto> getNewsDatas() throws IOException {

		List<NewsDto> NewsDtoList = new ArrayList<NewsDto>();
		Document doc = Jsoup.connect(NEWS_DATAS_URL).get();

		Elements contents = doc.select("ul.headline_list > li > dl > dt > a");

		for (Element element : contents) {
			
			NewsDto newsDto = new NewsDto();
			

			String link_naver = "https://land.naver.com/";
			String link = link_naver.concat(element.attr("href"));
			String title = element.ownText();

			if(title.length() > 5) {
				newsDto.setNewsTitle(title);
				newsDto.setNewsLink(link);
				NewsDtoList.add(newsDto);			
			}

		}

		Elements contents2 = doc.select("ul.headline_list > li > dl > dd");

		int i = 0;
		for (Element element : contents2) {
			
			NewsDto newsDto = NewsDtoList.get(i);
			
			// 기사 본문
			String content = element.ownText();
			
			// 작성자
			String str = element.getElementsByClass("writing").toString();
			String writing = str.substring(22, str.length()-7);
			
			// 날짜
			String str_date = element.getElementsByClass("date").toString();
			String date = str_date.substring(19, str_date.length()-7);

			newsDto.setNewsContent(content);
			newsDto.setNewsWriting(writing);
			newsDto.setNewsDate(date);
			
			i++;
		}
		
		return NewsDtoList;
		
	}

}
