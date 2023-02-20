package utils;

import models.Project;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AssertUtils {

    public static boolean assertEquals(List<Project> actual, List<Project> expected) {
        for (int i = 0; i < actual.size(); i++) {

            actual.get(i).setName(replaceEmptyLine(actual.get(i).getName()));
            actual.get(i).setMethod(replaceEmptyLine(actual.get(i).getMethod()));
            actual.get(i).setStatus(replaceEmptyLine(actual.get(i).getStatus()));
            actual.get(i).setStartTime(replaceEmptyLine(actual.get(i).getStartTime()));
            actual.get(i).setEndTime(replaceEmptyLine(actual.get(i).getEndTime()));
            actual.get(i).setDuration(replaceEmptyLine(actual.get(i).getDuration()));

            expected.get(i).setName(replaceEmptyLine(expected.get(i).getName()));
            expected.get(i).setMethod(replaceEmptyLine(expected.get(i).getMethod()));
            expected.get(i).setStatus(replaceEmptyLine(expected.get(i).getStatus()));
            expected.get(i).setStartTime(replaceEmptyLine(expected.get(i).getStartTime()));
            expected.get(i).setEndTime(replaceEmptyLine(expected.get(i).getEndTime()));
            expected.get(i).setDuration(replaceEmptyLine(expected.get(i).getDuration()));


            if (!actual.get(i).getName().toLowerCase(Locale.ROOT).equals(
                    expected.get(i).getName().toLowerCase(Locale.ROOT))) {
                return false;
            }
            if (!actual.get(i).getMethod().toLowerCase(Locale.ROOT).equals(
                    expected.get(i).getMethod().toLowerCase(Locale.ROOT))) {
                return false;
            }
            if (!actual.get(i).getStatus().toLowerCase(Locale.ROOT).equals(
                    expected.get(i).getStatus().toLowerCase(Locale.ROOT))) {
                return false;
            }
            if (!actual.get(i).getStartTime().toLowerCase(Locale.ROOT).equals(
                    expected.get(i).getStartTime().toLowerCase(Locale.ROOT))) {
                return false;
            }
            if (!actual.get(i).getEndTime().toLowerCase(Locale.ROOT).equals(
                    expected.get(i).getEndTime().toLowerCase(Locale.ROOT))) {
                return false;
            }
            if (!actual.get(i).getDuration().toLowerCase(Locale.ROOT).equals(
                    expected.get(i).getDuration().toLowerCase(Locale.ROOT))) {
                return false;
            }
        }
        return true;
    }

    private static String replaceEmptyLine(String value) {
        if (Objects.equals(value, "") || Objects.equals(value, null)) {
            return "null";
        }
        return value;
    }
}