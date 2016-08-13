package dk.fitfit.remotetexting.api.resource.assembler;

import org.springframework.hateoas.Link;

import java.util.List;


public interface ResourceLinksAssemblerInterface<T> {
	List<Link> getLinks(T entity);
}
