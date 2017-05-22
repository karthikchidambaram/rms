package com.i2g.rms.rest.mapping;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link MapperService} for wrapping the Dozer library and
 * providing additional mapping functionality.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Service
public class DozerMapperService implements MapperService {
	/** Underlying Dozer {@link Mapper} to delegate mapping to. */
	@Autowired
	private Mapper _modelMapper;
	
	public Mapper getModelMapper() {
		return _modelMapper;
	}

	public void setModelMapper(Mapper modelMapper) {
		_modelMapper = modelMapper;
	}

	@Override
	public <T> T map(final Object source, final Class<T> outputClass) {
		return _modelMapper.map(source, outputClass);
	}

	@Override
	public <T> T map(final Object source, final T output) {
		_modelMapper.map(source, output);
		return output;
	}

	@Override
	public <T> List<T> map(final List<?> inputList, final Class<T> outputClass) {
		return inputList == null ? null
				: (inputList.isEmpty() ? Collections.emptyList()
						: inputList.stream().map(o -> _modelMapper.map(o, outputClass)).collect(Collectors.toList()));
	}

	@Override
	public <T> Set<T> map(final Set<?> inputSet, final Class<T> outputClass) {
		return inputSet == null ? null
				: (inputSet.isEmpty() ? Collections.emptySet()
						: inputSet.stream().map(o -> _modelMapper.map(o, outputClass)).collect(Collectors.toSet()));
	}

	@Override
	public <K, V> Map<K, V> map(final Map<K, ?> inputMap, final Class<V> outputClass) {
		return inputMap == null ? null
				: (inputMap.isEmpty() ? Collections.emptyMap()
						: inputMap.entrySet().stream().collect(
								Collectors.toMap(e -> e.getKey(), e -> _modelMapper.map(e.getValue(), outputClass))));
	}

	@Override
	public <K, V> Map<K, V> map(final Map<?, ?> inputMap, final Class<K> outputKeyClass,
			final Class<V> outputValueClass) {
		return inputMap == null ? null
				: (inputMap.isEmpty() ? Collections.emptyMap()
						: inputMap.entrySet().stream()
								.collect(Collectors.toMap(e -> _modelMapper.map(e.getKey(), outputKeyClass),
										e -> _modelMapper.map(e.getValue(), outputValueClass))));
	}
}
