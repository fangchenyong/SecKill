package com.joey.seckill.controller;

import com.joey.seckill.pojo.User;
import com.joey.seckill.redis.GoodsKey;
import com.joey.seckill.redis.RedisService;
import com.joey.seckill.service.GoodsService;
import com.joey.seckill.service.UserService;
import com.joey.seckill.vo.GoodsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	UserService userService;
	
	@Autowired
	RedisService redisService;

	@Autowired
	GoodsService goodsService;

	@Autowired
	ThymeleafViewResolver thymeleafViewResolver;

    @RequestMapping(value = "/to_list",produces = "text/html")
	@ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, User user) {
    	model.addAttribute("user", user);
//		List<GoodsVo> goodsList = goodsService.listGoodsVo();
//		System.out.println(goodsList);
//		model.addAttribute("goodsList",goodsList);
//        return "goodsList";

		//取缓存
		String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
		if(!StringUtils.isEmpty(html)) {
			return html;
		}
		List<GoodsVo> goodsList = goodsService.listGoodsVo();
		model.addAttribute("goodsList", goodsList);
//    	 return "goods_list";
//		SpringWebContext ctx = new SpringWebContext(request,response,
//				request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );

		IWebContext ctx = new WebContext(request,response,
				request.getServletContext(),request.getLocale(),model.asMap());
		//手动渲染
		html = thymeleafViewResolver.getTemplateEngine().process("goodsList", ctx);
		if(!StringUtils.isEmpty(html)) {
			redisService.set(GoodsKey.getGoodsList, "", html);
		}
		return html;

    }


	@RequestMapping(value = "/to_detail/{goodsId}",produces = "text/html")
	@ResponseBody
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model,User user,
						 @PathVariable("goodsId")long goodsId) {
		model.addAttribute("user", user);
//		System.out.println("当前登录："+user.getId());
//		GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
//		model.addAttribute("goods", goods);
//		//秒杀开始时间
//		long startAt = goods.getStartDate().getTime();
//		//秒杀结束时间
//		long endAt = goods.getEndDate().getTime();
//		//当前时间
//		long now = System.currentTimeMillis();
//
//
//		System.out.println(goods.getStartDate());
//		System.out.println(goods.getEndDate());
//		System.out.println(now);
//
//
//		int seckillStatus = 0;
//		int remainSeconds = 0;
//		//秒杀还没开始，倒计时
//		if(now < startAt ) {
//			seckillStatus = 0;
//			remainSeconds = (int)((startAt - now )/1000);
//		}
//		//秒杀已经结束
//		else  if(now > endAt){
//			seckillStatus = 2;
//			remainSeconds = -1;
//		}
//		//秒杀进行中
//		else {
//			seckillStatus = 1;
//			remainSeconds = 0;
//		}
//		model.addAttribute("seckillStatus", seckillStatus);
//		model.addAttribute("remainSeconds", remainSeconds);
//		return "goodsDetail";


		//取缓存
		String html = redisService.get(GoodsKey.getGoodsDetail, ""+goodsId, String.class);
		if(!StringUtils.isEmpty(html)) {
			return html;
		}
		//手动渲染
		GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
		model.addAttribute("goods", goods);

		long startAt = goods.getStartDate().getTime();
		long endAt = goods.getEndDate().getTime();
		long now = System.currentTimeMillis();

		int seckillStatus = 0;
		int remainSeconds = 0;
		if(now < startAt ) {//秒杀还没开始，倒计时
			seckillStatus = 0;
			remainSeconds = (int)((startAt - now )/1000);
		}else  if(now > endAt){//秒杀已经结束
			seckillStatus = 2;
			remainSeconds = -1;
		}else {//秒杀进行中
			seckillStatus = 1;
			remainSeconds = 0;
		}
		model.addAttribute("seckillStatus", seckillStatus);
		model.addAttribute("remainSeconds", remainSeconds);
//        return "goods_detail";

//		SpringWebContext ctx = new SpringWebContext(request,response,
//				request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
		IWebContext ctx = new WebContext(request,response,
				request.getServletContext(),request.getLocale(), model.asMap());
		html = thymeleafViewResolver.getTemplateEngine().process("goodsDetail", ctx);
		if(!StringUtils.isEmpty(html)) {
			redisService.set(GoodsKey.getGoodsDetail, ""+goodsId, html);
		}
		return html;
	}
    
}
