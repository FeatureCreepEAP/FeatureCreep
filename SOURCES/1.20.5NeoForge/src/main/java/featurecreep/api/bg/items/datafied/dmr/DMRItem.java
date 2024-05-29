/**
 * 
 */
package featurecreep.api.bg.items.datafied.dmr;

import org.jboss.dmr.ModelNode;

import featurecreep.api.bg.items.datafied.DatafiedItem;

/**
 * @author rhel
 *
 */
public interface DMRItem<T> extends DatafiedItem<T> {

	public ModelNode toModelNode();

}
