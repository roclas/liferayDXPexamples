package configPortlet.portlet;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import HelloService.HelloService;
import aQute.bnd.annotation.metatype.Configurable;
import configPortlet.constants.ConfigPortletKeys;

/**
 * @author carlos
 */
@Component(
	configurationPid = "com.roclas.ColorConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=configPortlet Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ConfigPortletKeys.Config,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ConfigPortlet extends MVCPortlet {
	
	@Reference HelloService service;
	private BundleContext bundleContext;
	
	@Activate
	@Modified
    protected void activate(BundleContext bc, Map<String, Object> properties) {
		_configuration = Configurable.createConfigurable( ColorConfiguration.class, properties);
		System.out.println("activation; color ="+_configuration.color());
		bundleContext=bc;
        getService();
    }


	private void getServiceIfNull() { if(service==null)getService(); }

	private void getService() {
        // track all the impls where each one includes it's own type in the published service types (i.e. "objectClass")
        //_map = ServiceTrackerMapFactory.openSingleValueMap(bundleContext, HelloService.class, "objectClass");
		_map = ServiceTrackerMapFactory.openSingleValueMap(bundleContext, HelloService.class, "impltype");
		Iterator<String> it = _map.keySet().iterator();
		service=_map.getService(_configuration.serviceImpl());
		while(it.hasNext() && service==null){
			String k=it.next();
			service=_map.getService(k);
		}
	}

	
	@Override
	public void doView( RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
		System.out.println(_configuration.color());
		renderRequest.setAttribute( ColorConfiguration.class.getName(), _configuration);
		renderRequest.setAttribute( "color", _configuration.color());
		getService();
		renderRequest.setAttribute( "serviceString", service.sayHello());
		renderRequest.setAttribute( "service", service);

		super.doView(renderRequest, renderResponse);
	}

	private volatile ColorConfiguration _configuration;
	private ServiceTrackerMap<String, HelloService> _map;
}