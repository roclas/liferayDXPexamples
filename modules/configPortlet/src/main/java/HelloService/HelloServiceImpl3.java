package HelloService;

import org.osgi.service.component.annotations.Component;

@Component(service=HelloService.class,property={"impltype=impl3"})
public class HelloServiceImpl3 implements HelloService {

	@Override
	public String say() { return "implementation3"; }

	@Override
	public String say(String what) { return "impl 3 > "+what; }

	@Override
	public String sayHello() {
		return "hello from impl3";
	}

}
