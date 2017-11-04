This package contains an entity using dependeny injections (i. e. rich entity): Meeting uses an injected PersonRepository
for looking up persons for registration of participants.

The domain service MeetingOptimizeService demonstrates two things:
- the usage of domain events for publishing, that someting happened.
- the effect of inversion of control: Firing an event triggers some action in the adapter layer without direct coupling.

In a real life application this package could become a separate depoloyment unit. Of course, in that case, accesses to
Person and Room would require remoting (the classic enterprise application scenario) or data duplication (microservice scenario). 