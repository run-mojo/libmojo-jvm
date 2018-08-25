package run.mojo.example.actions.user;

import javax.inject.Inject;
import javax.inject.Provider;
import run.mojo.SliceScope;
import run.mojo.actor.ActorContext;
import run.mojo.actor.ActorService;
import run.mojo.actor.AskDescriptor;
import run.mojo.actor.StreamDescriptor;
import run.mojo.actor.TellDescriptor;
import run.mojo.message.MessageCodec;

/**
 *
 */
@SliceScope
public class UserService extends
    ActorService<UserService, UserActor, ActorContext<UserActor>, UserActorAddr> {

  @Inject
  UserService(
      Provider<UserActor> actorProvider,
      Provider<UserActorAddr> userActorProducerProvider) {
    super(
        UserService.class,
        UserActor.class,
        ActorContext.class,
        UserActorAddr.class,
        actorProvider,
        userActorProducerProvider,
        new AskDescriptor[]{
            AskDescriptor.create(
                MessageCodec.create(CreateUser.Request.class),
                MessageCodec.create(CreateUser.Response.class),
                UserActor.class,
                "create"
            ),
            AskDescriptor.create(
                MessageCodec.create(CreateUser.Request.class),
                MessageCodec.create(CreateUser.Response.class),
                UserActor.class,
                "update"
            ),
            AskDescriptor.create(
                MessageCodec.create(DeleteUser.Request.class),
                MessageCodec.create(DeleteUser.Response.class),
                UserActor.class,
                "delete"
            )
        },
        // Define all Tells
        new TellDescriptor[]{
            TellDescriptor.create(
                MessageCodec.create(UserEvent.class),
                UserActor.class,
                "on"
            )
        },
        new StreamDescriptor[0]
    );
  }
}
