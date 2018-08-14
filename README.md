# RoadAccidentDataAnalysis-MajorProject

# Project Definition:
This is a data analysis project in which we try to analyze a large dataset not capable of being analyzed by typical database or data analysis software like Excel, Weka or RapidMiner. To overcome this, we try to implement distributed processing using Hadoop.

# Project Scope
Traffic engineer can compare various road segments for optimization. Government agencies can run instructional recommendation system.
Tools Required:
- Hadoop MapReduce: Hadoop MapReduce is a programming model for processing large data sets with a parallel, distributed algorithm on a cluster

Why Hadoop?

Processing accident data in conventional data mining softwares like Weka or RapidMiner is sure possible, but these software take a long time and are restricted in terms of memory. Hadoop lets us harness the power of parallel and distributed processing of the data to generate the result in tangible amount of time
- Eclipse (Lunar): IDE for developing the Java mapper and reducer code.

# Road Safety Data
These files provide detailed road safety data about the circumstances of personal injury road accidents in GB from 1979, the types (including Make and Model) of vehicles involved and the consequential casualties. The statistics relate only to personal injury accidents on public roads that are reported to the police, and subsequently recorded, using the STATS19 accident reporting form.
All the data variables are coded rather than containing textual strings. The lookup tables are available in the "Additional resources" section towards the bottom of the table. Accident, Vehicle and Casualty data for individual years 2005 - 2009 have now been removed but are available on request from roadacc.stats@dft.gsi.gov.uk. Data for these years is also available within the 2005 - Latest Year datasets.
Also includes: Results of breath-test screening data from recently introduced digital breath testing devices, as provided by Police Authorities in England and Wales.

# Results
At the end it has been found that while the most of the accidents have occurred due to one of the few primary reasons for crashes in most of the situations, but some of the findings are really insightful and some areas need special care in terms of maintenance and infrastructure.
The critical findings like Road types and Light conditions are the areas which can be further explored using detailed data and there is a lot of scope for traffic safety improvement as well as saving government expenditure in terms of maintenance.

# Conclusion:
The patterns generated after analysis are pretty accurate and insightful. Further room for improvement exists by adding more clusters to the distributed processing module, using software like Tableau and other commercial suits for more user friendly visualizations and more thorough data preprocessing to come up with more cost effective insights.
