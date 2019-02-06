package com.allphoneleads.api.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.allphoneleads.api.service.DefinitionManager;

public class BuildAllocationForOffers {

	private static final Logger logger = LoggerFactory.getLogger(BuildAllocationForOffers.class);

	/*
	 * arr[] ---> Input Array data[] ---> Temporary array to store current
	 * combination start & end ---> Staring and Ending indexes in arr[] index
	 * ---> Current index in data[] r ---> Size of a combination to be printed
	 */

	public List<String> offerAllocationList = null;
	
	

	public BuildAllocationForOffers() {
		offerAllocationList =  new ArrayList<String>();
	}

	private void combinationUtil(int arr[], int n, int r, int index, int data[],
			int i) {
		// Current combination is ready to be printed, print it
		if (index == r) {
			String combi = "";
			for (int j = 0; j < r; j++) {
				// System.out.print(data[j] + " ");
				combi = combi + data[j] + "-";
			}
			combi= combi.substring(0,combi.lastIndexOf("-") );
			offerAllocationList.add(combi);
			// System.out.println(" ");

			return;
		}

		// When no more elements are there to put in data[]
		if (i >= n)
			return;

		// current is included, put next at next location
		data[index] = arr[i];
		combinationUtil(arr, n, r, index + 1, data, i + 1);

		// current is excluded, replace it with next (Note that
		// i+1 is passed, but index is not changed)
		combinationUtil(arr, n, r, index, data, i + 1);
	}

	// The main function that prints all combinations of size r
	// in arr[] of size n. This function mainly uses combinationUtil()
	private void printCombination(int arr[], int n, int r) {
		// A temporary array to store all combination one by one
		int data[] = new int[r];

		// Print all combination using temprary array 'data[]'
		this.combinationUtil(arr, n, r, 0, data, 0);
	}

	/* Driver function to check for above function */
	public void find(int[] args) {
	
		int n = args.length;
		for (int r = 2; r <= args.length; r++) {
			printCombination(args, n, r);

		}
		logger.debug("total allocation for a given : {}", offerAllocationList.size());
		for (String combi : offerAllocationList) {
			logger.debug("{}", combi);
		}
	}

	/*
	 * int r2 = 4; printCombination(arr, n, r2);
	 * System.out.println("-------------"); int r3 = 5; printCombination(arr, n,
	 * r3); System.out.println("-------------");
	 */

}
