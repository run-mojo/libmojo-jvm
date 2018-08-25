package run.mojo.actor.example;

import run.mojo.message.Result;

/**
 *
 */
public interface MyActorHandler {

  Result<CreateResponse> create(CreateRequest request);

  Result<DeleteResponse> delete(DeleteRequest request);

  class CreateRequest {

  }

  class CreateResponse {

  }


  class DeleteRequest {

  }

  class DeleteResponse {

  }
}
