package dk.fitfit.remotetexting.api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FooController {

	@PreAuthorize("#oauth2.hasScope('read')")
	@RequestMapping(method = RequestMethod.GET, value = "/foos/{id}")
	@ResponseBody
	public Foo findById(@PathVariable long id) {
		return new Foo(id, "ssss");
//				new Foo(Long.parseLong(randomNumeric(2)), randomAlphabetic(4));
	}
}
