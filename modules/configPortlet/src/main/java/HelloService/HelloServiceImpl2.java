package HelloService;

import org.osgi.service.component.annotations.Component;

@Component(service=HelloService.class,property={"impltype=impl2"})
public class HelloServiceImpl2 implements HelloService {

	@Override
	public String say() { return "implementation2"; }

	@Override
	public String say(String what) { return "impl 2 > "+what; }

	@Override
	public String sayHello() {
		return "hello from impl2";
	}

}
