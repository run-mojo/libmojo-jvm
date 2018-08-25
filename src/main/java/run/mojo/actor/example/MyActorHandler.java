package run.mojo.actor.example;

import run.mojo.actor.Ask;

/**
 *
 */
public interface MyActorHandler {

  Ask<CreateResponse> create(CreateRequest request);

  Ask<DeleteResponse> delete(DeleteRequest request);

  class CreateRequest {

  }

  class CreateResponse {

  }


  class DeleteRequest {

  }

  class DeleteResponse {

  }
}
