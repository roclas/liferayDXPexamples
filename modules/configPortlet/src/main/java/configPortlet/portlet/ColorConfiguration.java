package configPortlet.portlet;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD( id = "com.roclas.ColorConfiguration")
public interface ColorConfiguration {

		@Meta.AD(required = false)
		public String color();
		
		@Meta.AD(required = true, deflt="impl1")
		public String serviceImpl();


}