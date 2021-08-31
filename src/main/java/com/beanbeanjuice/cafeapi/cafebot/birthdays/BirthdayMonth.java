package com.beanbeanjuice.cafeapi.cafebot.birthdays;

import org.jetbrains.annotations.NotNull;

/**
 * A static {@link BirthdayMonth} class used for {@link Birthday} months.
 *
 * @author beanbeanjuice
 */
public enum BirthdayMonth {

    JANUARY (1, 31),
    FEBRUARY (2, 29),
    MARCH (3, 31),
    APRIL (4, 30),
    MAY (5, 31),
    JUNE (6, 30),
    JULY (7, 31),
    AUGUST (8, 31),
    SEPTEMBER (9, 30),
    OCTOBER (10, 31),
    NOVEMBER (11, 30),
    DECEMBER (12, 31),
    ERROR (13, 31);

    private final Integer monthNumber;
    private final Integer daysInMonth;

    /**
     * Creates a new {@link BirthdayMonth} static class.
     * @param monthNumber The {@link Integer monthNumber}.
     * @param daysInMonth The amount of {@link Integer daysInMonth}.
     */
    BirthdayMonth(@NotNull Integer monthNumber, @NotNull Integer daysInMonth) {
        this.monthNumber = monthNumber;
        this.daysInMonth = daysInMonth;
    }

    /**
     * @return The {@link Integer monthNumber}.
     */
    @NotNull
    public Integer getMonthNumber() {
        return monthNumber;
    }

    /**
     * @return The amount of {@link Integer daysInMonth}.
     */
    @NotNull
    public Integer getDaysInMonth() {
        return daysInMonth;
    }
}
