package run.mojo.actor;

import run.mojo.message.MessageCodec;

/**
 *
 */
public class Registry {

  final MessageCodec[] codecs;
  final ActorRuntime[] actors;
  final AskDescriptor[] asks;
  final TellDescriptor[] tells;
  final StreamDescriptor[] streams;

  private ActorService[] services;

  public Registry(
      MessageCodec[] codecs,
      ActorRuntime[] actors,
      AskDescriptor[] asks,
      TellDescriptor[] tells,
      StreamDescriptor[] streams) {
    this.codecs = codecs;
    this.actors = actors;
    this.asks = asks;
    this.tells = tells;
    this.streams = streams;
  }

  public Registry copy() {
    return new Registry(codecs, actors, asks, tells, streams);
  }
}
