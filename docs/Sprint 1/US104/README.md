LAPR3 2021-2022 Integrative Project - Sprint 1
=============================================================================
## JIRA Issue: LAP22G99-4 ##


## [US102] As a traffic manager I which to search the details of a ship using any of its codes: MMSI, IMO or Call Sign.
Brief description: This user story’s goal is to make a Summary of a ship’s movements. It must present one of three codes  (MMSI, IMO or Call Sign), Vessel Name, Start Base Date Time, End Base Date Time, Total Movement Time, Total Number of Movements, Max SOG, Mean SOG, Max COG, Mean COG, Departure Latitude, Departure Longitude, Arrival Latitude, Arrival Longitude, Travelled Distance (incremental sum of the distance between each positioning message) and Delta Distance (linear distance between the coordinates of the first and last move). To start the user story it is asked to use of the three codes above and the decision to choose one of these codes is up to the Traffic Manager. After choosing one of the three codes, the ship´s summary is presented.


## Main Actor:

Traffic Manager


## System Sequence Diagram (LAP22G99-16)

![SSD](US104-SSD.png)

## Domain Model (LAP22G99-16)

![SSD](US104-MD.png)

## Sequence Diagram (LAP22G99-17)

![SSD](US104-SD.png)

## Class Diagram (LAP22G99-17)

![SSD](US104-CD.png)


## LAP22G99-18

The code and its tests are located in the src folder.

## LAP22G99-19
## Project Status:

- Project on track? [Yes/No].
  - Yes.

## Sprint Goals:

- What was planned to achieve in this US?
  - It was planned to finish all the US as well as exceed test percentages;
  - Implement a UI to login with email and password.
- Roadmap elements you wanted to target.
  - None.
- Milestones in the sprint.
  - UI to login fully implemented;
  - Functional US;
  - Test percentages exceeded.


## Status overview:

- Planned sprint items:
  - US104
- Finished and unfinished:
  - Done: US / Tests;
  - Not done: nothing.
- Added and removed items:
  - Added: UI to login
  - Removed: none.
- Changed priorities:
  - Migration from Junit 4 to Junit 5: high priority.
- Test coverage
  - 91.7%.
- How to continue with incompleted work
  - Inexistent incompleted work.

## Impediments:

- What is left to finish in unfinished tasks:
  - None unfinished tasks.
- Risks identified in the sprint:
  - None.
- Impediments identified in the sprint:
  - None.
- Organization level impediments:
  - None.
- Proposals of solutions
  - None.
