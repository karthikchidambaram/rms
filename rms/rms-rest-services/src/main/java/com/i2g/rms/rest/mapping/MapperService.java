package com.i2g.rms.rest.mapping;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface for defining common methods for mapping objects.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public interface MapperService {
	/**
	 * Maps the specified {@code source} object to a new object of the type
	 * {@code outputclass}.
	 * 
	 * @param <T> expected conversion type
	 * @param source
	 * @param outputClass
	 * @return converted object
	 */
	<T> T map(final Object source, final Class<T> outputClass);
	
	/**
	 * Maps the specified {@code source} object to the existing {@code output}
	 * instance of type {@code T}.
	 *  
	 * @param <T> type of output
	 * @param source
	 * @param output
	 * @return output instance with overlaid fields
	 */
	<T> T map(final Object source, final T output);
	
	/**
	 * Maps the specified {@code inputList} of objects to a new list populated
	 * with new instances of the type {@code outputClass}.  If the input list is
	 * {@code null}, then {@code null} is returned.
	 * 
	 * 
	 * @param <T> expected conversion type
	 * @param inputList
	 * @param outputClass
	 * @return converted list of objects
	 */
	<T> List<T> map(final List<?> inputList, final Class<T> outputClass);
	
	/**
	 * Maps the specified {@code inputSet} of objects to a new set populated
	 * with new instances of the type {@code outputClass}.  If the input set is
	 * {@code null}, then {@code null} is returned.
	 * 
	 * <p><strong>Note:</strong> Please note the specified output set is 
	 * affected by the implementation of {@link Object#equals(Object)} and
	 * {@link Object#hashCode()} methods of the target {@code outputClass}; the
	 * output set may contain a different number of values if these methods do 
	 * not match similar functionality as the source class.</p>
	 * 
	 * @param <T> expected conversion type
	 * @param inputSet
	 * @param outputClass
	 * @return converted set of objects
	 */
	<T> Set<T> map(final Set<?> inputSet, final Class<T> outputClass);
	
	/**
	 * Maps the specified {@code inputMap} of objects to a new map populated
	 * with new value instances of the type {@code outputClass}, assuming the 
	 * keys of remain unchanged.  If the input map is {@code null}, then 
	 * {@code null} is returned.
	 * 
	 * @param <K> type of key in input and output maps
	 * @param <V> type of expected conversion value in map
	 * @param inputMap
	 * @param outputClass
	 * @return converted map of objects
	 */
	<K, V> Map<K, V> map(final Map<K, ?> inputMap, final Class<V> outputClass);
	
	/**
	 * Maps the specified {@code inputMap} of objects to a new map populated
	 * with new key instances of the type {@code outputKeyClass} and new value 
	 * instances of the type {@code outputValueClass}.  If the input map is 
	 * {@code null}, then {@code null} is returned.
	 * 
	 * <p><strong>Note:</strong> Please note the specified output map is 
	 * affected by the implementation of {@link Object#equals(Object)} and
	 * {@link Object#hashCode()} methods of the target {@code outputKeyClass}; 
	 * the output map may contain a different number of values if these methods 
	 * do not match similar functionality as the source class.</p>
	 * 
	 * @param <K> type of key in input and output maps
	 * @param <V> type of expected conversion value in map
	 * @param inputMap
	 * @param outputKeyClass for converting map keys
	 * @param outputValueClass for converting map values
	 * @return converted map of objects
	 */
	<K, V> Map<K, V> map(final Map<?, ?> inputMap, final Class<K> outputKeyClass, final Class<V> outputValueClass);
}
