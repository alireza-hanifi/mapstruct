/**
 *  Copyright 2012-2016 Gunnar Morling (http://www.gunnarmorling.de/)
 *  and/or other contributors as indicated by the @authors tag. See the
 *  copyright.txt file in the distribution for a full listing of all
 *  contributors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.mapstruct.ap.internal.conversion;

import org.mapstruct.ap.internal.model.common.ConversionContext;
import org.mapstruct.ap.internal.util.NativeTypes;
import org.mapstruct.ap.internal.util.Strings;

/**
 * Conversion between primitive types such as {@code byte} or {@code long} and
 * {@link String}.
 *
 * @author Gunnar Morling
 */
public class PrimitiveToStringConversion extends SimpleConversion {

    private final Class<?> sourceType;
    private final Class<?> wrapperType;

    public PrimitiveToStringConversion(Class<?> sourceType) {
        if ( !sourceType.isPrimitive() ) {
            throw new IllegalArgumentException( sourceType + " is no primitive type." );
        }

        this.sourceType = sourceType;
        this.wrapperType = NativeTypes.getWrapperType( sourceType );
    }

    @Override
    public String getToExpression(ConversionContext conversionContext) {
        return "String.valueOf( <SOURCE> )";
    }

    @Override
    public String getFromExpression(ConversionContext conversionContext) {
        return wrapperType.getSimpleName() + ".parse" +
            Strings.capitalize( sourceType.getSimpleName() ) + "( <SOURCE> )";
    }
}
