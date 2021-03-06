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

package com.liferay.dynamic.data.mapping.io;

import com.liferay.dynamic.data.mapping.annotations.DDMForm;
import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
import com.liferay.dynamic.data.mapping.io.internal.DDMFormFieldTypeSettingsSerializerHelper;
import com.liferay.dynamic.data.mapping.io.internal.DDMFormJSONSerializerImpl;
import com.liferay.dynamic.data.mapping.io.internal.DDMFormLayoutJSONSerializerImpl;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;

import org.junit.Test;

import org.skyscreamer.jsonassert.JSONAssert;

/**
 * @author Marcellus Tavares
 */
public class DDMFormFieldTypeSettingsSerializerHelperTest
	extends BaseDDMFormSerializerTestCase {

	@Test
	public void testGetSettingsLayout() throws Exception {
		JSONFactory jsonFactory = new JSONFactoryImpl();

		DDMFormFieldTypeSettingsSerializerHelper
			ddmFormFieldTypeSettingsSerializerHelper =
				new DDMFormFieldTypeSettingsSerializerHelper(
					SampleDDMFormFieldTypeSettings.class,
					new DDMFormJSONSerializerImpl(),
					new DDMFormLayoutJSONSerializerImpl(), jsonFactory);

		String expectedJSON = read(
			"ddm-form-field-type-settings-layout-serializer-test-data.json");

		JSONObject actualJSONObject =
			ddmFormFieldTypeSettingsSerializerHelper.
				getSettingsLayoutJSONObject();

		JSONAssert.assertEquals(
			expectedJSON, actualJSONObject.toString(), false);
	}

	@DDMForm
	private interface SampleDDMFormFieldTypeSettings {

		@DDMFormField(
			properties = {"setting.category=basic", "setting.weight=1"}
		)
		public String a();

		@DDMFormField(
			properties = {"setting.category=basic", "setting.weight=2"}
		)
		public String b();

		@DDMFormField(
			properties = {"setting.category=basic", "setting.weight=3"}
		)
		public String c();

		@DDMFormField
		public String d();

		@DDMFormField(
			properties = {"setting.category=advanced", "setting.weight=1"}
		)
		public String e();

	}

}