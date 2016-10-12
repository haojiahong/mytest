package com.hao.mytest.designpatterns.owndo.builder;

/**
 * Created by haojiahong on 16/10/12.
 */
public abstract class ActorBuilder {
    protected Actor actor = new Actor();

    public abstract void buildHeight();

    public abstract void buildWeight();

    public abstract void buildHair();

    public abstract void buildType();

    public Actor createActor() {
        return actor;
    }

    public static void constructActor(ActorBuilder actorBuilder) {
        actorBuilder.buildHair();
        actorBuilder.buildHeight();
        actorBuilder.buildType();
        actorBuilder.buildWeight();
        Actor actor = actorBuilder.createActor();
        System.out.println(actor.getHair());

    }

    public static void main(String[] args) {
        ActorBuilder actorBuilder = new AngelActorBuilder();
        ActorBuilder.constructActor(actorBuilder);
    }
}
