package com.hao.mytest.designpatterns.owndo.builder;

/**
 * Created by haojiahong on 16/10/12.
 */
public class AngelActorBuilder extends ActorBuilder {
    @Override
    public void buildHeight() {
        super.actor.setHeight("天使身高");
    }

    @Override
    public void buildWeight() {
        super.actor.setWeight("天使体重");
    }

    @Override
    public void buildHair() {
        super.actor.setHair("天使头发");
    }

    @Override
    public void buildType() {
        super.actor.setType("天使");
    }

}
