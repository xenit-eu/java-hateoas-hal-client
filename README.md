# ContentGrid HAL Forms Client

## Context

This is a minimal HAL and HAL-FORMS client, to be used with integration tests. 

## Usage

```
var halFormsClient = HalFormsClient.builder().restClient(restClient).build();

var orgs = halFormsClient.follow(HalLink.from(URI.create("http://api.contentgrid.test/orgs")));
var myOrg = halFormsClient.requestDefaultTemplate(orgs)
        .properties(Map.of("display_name", "test"))
        .execute().toHalDocument();

var projects = halFormsClient.follow(myOrg.getRequiredLink("projects"));
var testProject = halFormsClient.requestDefaultTemplate(projects)
        .properties(Map.of("name", "test"))
        .execute().toHalDocument();

```

## Possible improvements

* [ ] More explicit support for OAuth2 clients
* [ ] Use Spring HATEOAS concepts: `RepresentationModel<T>`
* [ ] Use Spring HATEOAS / Traverson concepts: `LinkRelation`, `LinkDiscoverer`, ...
* [ ] Base internals on `RestOperations` to improve testability, potentially using `RestOperationsAdapter`
* [ ] Add support to automatically follow redirects (HTTP 302)
* [ ] Let `DefaultHalFormsClient` extend `DefaultHalClient` to avoid duplication (possibly via ServiceLoader)

