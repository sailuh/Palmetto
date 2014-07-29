/**
 * Copyright (C) 2014 Michael Röder (michael.roeder@unister.de)
 *
 * Licensed under the Creative Commons Attribution-NonCommercial 4.0
 * International Public License (the "License").
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 * Unless required by applicable law or agreed to in writing, a file
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aksw.palmetto.weight;

import org.aksw.palmetto.data.SubsetProbabilities;

/**
 * This is an interface for a class that can be used to weight the single elements of a segmentation scheme.
 * 
 * @author m.roeder
 * 
 */
@Deprecated
public interface Weighter {

    public double[] createWeights(SubsetProbabilities probabilities);

    public String getName();
}
