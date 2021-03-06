/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.item.selector.criteria.audio.handler;

import com.liferay.item.selector.BaseItemSelectorCriterionHandler;
import com.liferay.item.selector.ItemSelectorCriterionHandler;
import com.liferay.item.selector.criteria.audio.criterion.AudioItemSelectorCriterion;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Roberto Díaz
 */
@Component(service = ItemSelectorCriterionHandler.class)
public class AudioItemSelectorCriterionHandler
	extends BaseItemSelectorCriterionHandler<AudioItemSelectorCriterion> {

	@Override
	public Class<AudioItemSelectorCriterion>
		getItemSelectorCriterionClass() {

		return AudioItemSelectorCriterion.class;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		super.activate(bundleContext);
	}

}