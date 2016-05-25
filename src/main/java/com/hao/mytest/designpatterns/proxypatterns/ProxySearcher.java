package com.hao.mytest.designpatterns.proxypatterns;

/**
 * Created by haojiahong on 16/5/24.
 */
public class ProxySearcher implements Searcher {
    private Searcher realSearcher = new RealSearcher();
    private AccessValidator accessValidator;
    private Logger logger;

    @Override
    public String doSearch(String userId, String keyword) {
        if (accessValidate(userId)) {
            String result = realSearcher.doSearch(userId, keyword);
            this.log(userId);
            return result;
        } else {
            return null;
        }
    }

    private boolean accessValidate(String userId) {
        accessValidator = new AccessValidator();
        return accessValidator.validate(userId);
    }

    private void log(String userId) {
        logger = new Logger();
        logger.log(userId);
    }
}
