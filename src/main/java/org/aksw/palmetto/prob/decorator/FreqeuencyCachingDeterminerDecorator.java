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
package org.aksw.palmetto.prob.decorator;

import java.util.Arrays;

import org.aksw.palmetto.data.CountedSubsets;
import org.aksw.palmetto.data.SegmentationDefinition;
import org.aksw.palmetto.prob.FrequencyDeterminer;

import com.carrotsearch.hppc.IntObjectOpenHashMap;

public class FreqeuencyCachingDeterminerDecorator extends AbstractSlidingWindowFrequencyDeterminerDecorator {

    private IntObjectOpenHashMap<int[]> cache = new IntObjectOpenHashMap<int[]>();

    public FreqeuencyCachingDeterminerDecorator(FrequencyDeterminer determiner) {
        super(determiner);
    }

    @Override
    public CountedSubsets[] determineCounts(String[][] wordsets, SegmentationDefinition[] definitions) {
        CountedSubsets countedSubsets[] = new CountedSubsets[definitions.length];
        int wordSetHash;
        String singleWordSet[][] = new String[1][];
        SegmentationDefinition singleDefinition[] = new SegmentationDefinition[1];
        for (int i = 0; i < definitions.length; ++i) {
            wordSetHash = Arrays.hashCode(wordsets[i]);
            if (cache.containsKey(wordSetHash)) {
                countedSubsets[i] = new CountedSubsets(definitions[i].segments, definitions[i].conditions,
                        cache.get(wordSetHash));
            } else {
                singleWordSet[0] = wordsets[i];
                singleDefinition[0] = definitions[i];
                countedSubsets[i] = this.determiner.determineCounts(singleWordSet, singleDefinition)[0];
                cache.put(wordSetHash, countedSubsets[i].counts);
            }
        }
        return countedSubsets;
    }
}
