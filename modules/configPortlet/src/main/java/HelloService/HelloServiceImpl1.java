package HelloService;
import org.osgi.service.component.annotations.Component;

@Component(service=HelloService.class,property={"impltype=impl1"})
public class HelloServiceImpl1 implements HelloService {

	@Override
	public String say() { return "implementation1"; }

	@Override
	public String say(String what) { return "impl 1 > "+what; }

	@Override
	public String sayHello() {
		return "hello from impl1";
	}

}
