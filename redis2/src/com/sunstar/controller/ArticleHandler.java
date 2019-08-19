package com.sunstar.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunstar.pojo.Article;
import com.sunstar.service.ArticleService;
import com.sunstar.utils.OpenOfficeUtil;
import com.sunstar.utils.PoiWordToHtml;

@Controller
@RequestMapping("article")
public class ArticleHandler {
	@Autowired
	private ArticleService articleService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private RedisTemplateUtil redisTemplateUtil;
    @Autowired
    private ServletContext servletContext;

	
	
	
@RequestMapping("search.action")	
public String search(Model model){	
	
	// redisTemplate.opsForValue().set("test", "redisTemplate测试");  	 
	 
   //  System.out.println(redisTemplate.opsForValue().get("test"));  

  //   redisTemplateUtil.set("utiltest", "redisTemplateUtil测试");
   //  System.out.println(redisTemplateUtil.get("utiltest"));

//备注:redis配置文件后面不要有空格！！！	
	
	
	List<Article> articleList=null;
	articleList = (List<Article>) redisTemplateUtil.getPopList("articleList");    //出栈   后把原来的元素删除的
/*    long listSize= redisTemplateUtil.getListLength("articleList");
    if(listSize>0){
     articleList=(List<Article>)redisTemplateUtil.getRangeList("articleList", 0, listSize-1);  //redis取出来多了一个"[]"，el表达式报错
    }	*/
    
     Date begTime=new Date();
     if(articleList==null) {
         System.out.println("还没有缓存，将从数据库中查询。。。");
          articleList=articleService.search();
         redisTemplateUtil.setList("articleList",articleList);        
    	 redisTemplateUtil.expire("articleList", 600, TimeUnit.SECONDS);                
     }else{  	 
         System.out.println("已经有缓存了。。。");
         redisTemplateUtil.setList("articleList",articleList);        
    	 redisTemplateUtil.expire("articleList", 600, TimeUnit.SECONDS);  
     }    
     System.out.println(articleList.toString());     
     model.addAttribute("articleList", articleList);	
     Date endTime = new Date();
     System.out.println(endTime.getTime()-begTime.getTime());
     
	return "atList";	
}	
	
@RequestMapping("deleteArticle.action")
public String deleteArticle(@RequestParam String aid,Model model){
	articleService.deleteById(aid);	
	return "forward:search.action";
}

	
	
	
	
	

@RequestMapping("listPage.action")
public String listPage(@RequestParam(required=false,defaultValue="1")Integer pageNum,
        @RequestParam(required=false,defaultValue="3")Integer pageSize,Model model){
	
	PageHelper.startPage(pageNum,pageSize);
	List<Article> articleList=articleService.search();
	
	PageInfo<Article> articleListPage=new PageInfo<Article>(articleList);	
	model.addAttribute("articleListPage", articleListPage);
	
	
	
	
//	PageHelper.startPage(pageNum,pageSize);                                              	
//	List<GoodsCatePojo> goodsCates=goodsService.findAllGoodsAndCate();		
//	PageInfo<GoodsCatePojo> pageHelper=new PageInfo<GoodsCatePojo>(goodsCates);	
//	model.addAttribute("pageHelper", pageHelper);
	
	return "pageList";	
}












@RequestMapping(value="showWordToHtml.action",method=RequestMethod.POST)
public void showWordToHtml(HttpServletResponse resp){
	String outPath=servletContext.getRealPath("");  //获取本项目的绝对路径
	
	try {
		PoiWordToHtml.wordToHtml(outPath);
	} catch (Throwable e) {
		e.printStackTrace();
	}
	
}

@RequestMapping(value="openOfficeShowWordToHtml.action",method=RequestMethod.POST)
public void openOfficeShowWordToHtml(HttpServletResponse resp){
	String path=servletContext.getRealPath("uploadFile");
	
   String htmlString=OpenOfficeUtil.toHtmlString(new File("D:\\Test\\test1.xlsx"),path);     //word可以    execl还报错！！
   resp.setContentType("text/html; charset=utf-8");
   try {
	resp.getWriter().print(htmlString);
} catch (IOException e) {

	e.printStackTrace();
}
   
   
	
	
	
}






	
	
	
}
