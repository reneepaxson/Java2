package edu.institution.asn9;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortAlgorithmMetrics {

	private List<Integer> intList = new ArrayList<Integer>();

	@SuppressWarnings("unchecked")
	public List<MetricData> retrieveMetrics(String filePath) {
		List<MetricData> timeList = new ArrayList<MetricData>();

		File file = new File(filePath);
		if (file.exists()) {
			try (FileInputStream fis = new FileInputStream(file);) {
				int i;
				while ((i = fis.read()) !=-1) {
					this.intList.add(i);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		} else {
			return null;
		}
		List<Integer> intList1 = intList.stream().collect(Collectors.toList());
		List<Integer> intList2 = intList.stream().collect(Collectors.toList());
		List<Integer> intList3 = intList.stream().collect(Collectors.toList());
		List<Integer> intList4 = intList.stream().collect(Collectors.toList());
		List<Integer> intList5 = intList.stream().collect(Collectors.toList());
		
		java.util.Collections.shuffle(intList1);
		java.util.Collections.shuffle(intList2);
		java.util.Collections.shuffle(intList3);
		java.util.Collections.shuffle(intList4);
		java.util.Collections.shuffle(intList5);
		
		Integer[] template = {};
		Integer[] intArray1 = intList1.toArray(template);
		Integer[] intArray2 = intList2.toArray(template);
		Integer[] intArray3 = intList3.toArray(template);
		Integer[] intArray4 = intList4.toArray(template);
		Integer[] intArray5 = intList5.toArray(template);
		
		MetricData merge = new MetricData(SortAlgorithm.MERGE_SORT);
		merge.setTimeComplexity(TimeComplexity.LOGARITHMIC);
		long start = System.currentTimeMillis();
		MergeSort.mergeSort(intArray2);
		long end = System.currentTimeMillis();
		merge.setExecutionTime(end - start);
		timeList.add(merge);
		System.out.println(merge);

		MetricData heap = new MetricData(SortAlgorithm.HEAP_SORT);
		heap.setTimeComplexity(TimeComplexity.LOGARITHMIC);
		start = System.currentTimeMillis();
		HeapSort.heapSort(intArray4);
		end = System.currentTimeMillis();
		heap.setExecutionTime(end - start);
		timeList.add(heap);
		System.out.println(heap);
		
		MetricData insert = new MetricData(SortAlgorithm.INSERTION_SORT);
		insert.setTimeComplexity(TimeComplexity.QUADRATIC);
		start = System.currentTimeMillis();
		InsertionSort.insertionSort(intArray5);
		end = System.currentTimeMillis();
		insert.setExecutionTime(end - start);
		timeList.add(insert);
		System.out.println(insert);
		
		MetricData quick = new MetricData(SortAlgorithm.QUICK_SORT);
		quick.setTimeComplexity(TimeComplexity.LOGARITHMIC);
		start = System.currentTimeMillis();
		QuickSort.quickSort(intArray3);
		end = System.currentTimeMillis();
		quick.setExecutionTime(end - start);
		timeList.add(quick);
		System.out.println(quick);

		MetricData bubble = new MetricData(SortAlgorithm.BUBBLE_SORT);
		bubble.setTimeComplexity(TimeComplexity.QUADRATIC);
		start = System.currentTimeMillis();
		BubbleSort.bubbleSort(intArray1);
		end = System.currentTimeMillis();
		bubble.setExecutionTime(end - start);
		timeList.add(bubble);
		System.out.println(bubble);


		java.util.Collections.sort(timeList);
		System.out.println(timeList);
		return timeList;
	}

}
