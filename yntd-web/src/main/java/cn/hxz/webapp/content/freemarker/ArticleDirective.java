package cn.hxz.webapp.content.freemarker; 

import java.io.IOException;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import cn.hxz.webapp.content.entity.Article;
import cn.hxz.webapp.content.service.ArticleService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import net.chenke.playweb.support.freemarker.DirectiveUtils;
import net.chenke.playweb.support.spring.ApplicationContextBean;

public class ArticleDirective implements TemplateDirectiveModel {
	
	public static final String PARAM_ID = "id";
	public static final String PARAM_USE_CACHE = "isCache";
	public static final boolean PARAM_USE_CACHE_DEFAULT = true;

	@SuppressWarnings("unchecked")
	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, 
			TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
    	
		// 取参数
		Long id = DirectiveUtils.getLong(params, PARAM_ID);
		boolean isCache = DirectiveUtils.getBoolean(params, PARAM_USE_CACHE, PARAM_USE_CACHE_DEFAULT);
    	
		ApplicationContext ctx = ApplicationContextBean.getApplicationContext();
		
		ArticleService bizArticle = ctx.getBean(ArticleService.class);
		
		Article item = null;
		
		if (id!=null){
			if (isCache)
				item = bizArticle.loadCached(id);
			else
				item = bizArticle.load(id);
		}

		loopVars[0] = DirectiveUtils.getBeansWrapper().wrap(item);
		body.render(env.getOut());
    }
	
}
