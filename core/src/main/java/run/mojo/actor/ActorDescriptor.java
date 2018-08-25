package run.mojo.actor;

/**
 *
 */
public class ActorDescriptor {

  Class<? extends MetalActor> actorClass;
  Class<? extends ActorContext> contextClass;
  Class<? extends Addr> addrClass;
  Class<? extends ActorService> serviceClass;
  AskDescriptor[] asks;
  TellDescriptor[] tells;
  StreamDescriptor[] streams;
  int id;
  ActorService[] services;

  public ActorDescriptor(
      Class<? extends MetalActor> actorClass,
      Class<? extends ActorContext> contextClass,
      Class<? extends Addr> addrClass,
      Class<? extends ActorService> serviceClass,
      AskDescriptor[] asks,
      TellDescriptor[] tells) {
    assert actorClass != null;
    assert contextClass != null;
    assert addrClass != null;
    assert serviceClass != null;
    this.actorClass = actorClass;
    this.contextClass = contextClass;
    this.addrClass = addrClass;
    this.serviceClass = serviceClass;
    this.asks = asks == null ? new AskDescriptor[0] : asks;
    this.tells = tells == null ? new TellDescriptor[0] : tells;
    this.streams = new StreamDescriptor[0];

    if (asks != null) {
      for (AskDescriptor ask : asks) {
        ask.actor = this;
      }
    }

    if (tells != null) {
      for (TellDescriptor tell : tells) {
        tell.actor = this;
      }
    }
  }

  public static ActorDescriptor create(
      Class<? extends MetalActor> actorClass,
      Class<? extends ActorContext> contextClass,
      Class<? extends Addr> addrClass,
      Class<? extends ActorService> serviceClass,
      AskDescriptor[] asks,
      TellDescriptor[] tells) {
    return new ActorDescriptor(actorClass, contextClass, addrClass, serviceClass, asks, tells);
  }
}

