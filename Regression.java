/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA.  
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * ---------------
 * Regression.java
 * ---------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 30-Sep-2002 : Version 1 (DG);
 * 18-Aug-2003 : Added 'abstract' (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 *
 */

package org.jfree.data.statistics;

import org.jfree.data.xy.XYDataset;

/**
 * A utility class for fitting regression curves to data.
 */
public abstract class Regression {

    /**
     * Returns the parameters 'a' and 'b' for an equation y = a + bx, fitted to
     * the data using ordinary least squares regression.  The result is 
     * returned as a double[], where result[0] --> a, and result[1] --> b.
     *
     * @param data  the data.
     *
     * @return The parameters.
     */
    /*
    public static double[] getOLSRegression(double[][] data) {

        int n = data.length;
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }

        double sumX = 0;
        double sumY = 0;
        double sumXX = 0;
        double sumXY = 0;
        for (int i = 0; i < n; i++) {
            double x = data[i][0];
            double y = data[i][1];
            sumX += x;
            sumY += y;
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
            sumXY += xy;
        }
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double xbar = sumX / n;
        double ybar = sumY / n;

        double[] result = new double[2];
        result[1] = sxy / sxx;
        result[0] = ybar - result[1] * xbar;

        return result;

    }
    */


    //after inline method -  getRes1(n, sumX, sumY, sumXX, sumXY);
    public static double[] getOLSRegression(double[][] data) {
        int n = data.length;
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data."); }
        double sumX = 0;
        double sumY = 0;
        double sumXX = 0;
        double sumXY = 0;
        for (int i = 0; i < n; i++) {
            double x = data[i][0];
            double y = data[i][1];
            sumX += x;
            sumY += y;
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
            sumXY += xy; }
        double xbar = sumX / n;
        double ybar = sumY / n;
        double[] result = new double[2];
        result[1] =  getRes1(n, sumX, sumY, sumXX, sumXY);
        result[0] = ybar - result[1] * xbar;
        return result;
    }


    /**
     * Returns the parameters 'a' and 'b' for an equation y = a + bx, fitted to 
     * the data using ordinary least squares regression. The result is returned 
     * as a double[], where result[0] --> a, and result[1] --> b.
     *
     * @param data  the data.
     * @param series  the series (zero-based index).
     *
     * @return The parameters.
     */
    /*
    public static double[] getOLSRegression(XYDataset data, int series) {

        int n = data.getItemCount(series);
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }

        double sumX = 0;
        double sumY = 0;
        double sumXX = 0;
        double sumXY = 0;
        for (int i = 0; i < n; i++) {
            double x = data.getXValue(series, i);
            double y = data.getYValue(series, i);
            sumX += x;
            sumY += y;
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
            sumXY += xy;
        }
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double xbar = sumX / n;
        double ybar = sumY / n;

        double[] result = new double[2];
        result[1] = sxy / sxx;
        result[0] = ybar - result[1] * xbar;

        return result;

    }*/

    //after inline method -  getRes1(n, sumX, sumY, sumXX, sumXY);
    public static double[] getOLSRegression(XYDataset data, int series) {
        int n = data.getItemCount(series);
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data."); }
        double sumX = 0;
        double sumY = 0;
        double sumXX = 0;
        double sumXY = 0;
        for (int i = 0; i < n; i++) {
            double x = data.getXValue(series, i);
            double y = data.getYValue(series, i);
            sumX += x;
            sumY += y;
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
            sumXY += xy; }
        double xbar = sumX / n;
        double ybar = sumY / n;
        double[] result = new double[2];
        result[1] = getRes1(n, sumX, sumY, sumXX, sumXY);
        result[0] = ybar - result[1] * xbar;
        return result;
    }

    /**
     * Returns the parameters 'a' and 'b' for an equation y = ax^b, fitted to 
     * the data using a power regression equation.  The result is returned as 
     * an array, where double[0] --> a, and double[1] --> b.
     *
     * @param data  the data.
     *
     * @return The parameters.
     */
    //after inline method -  getRes1(n, sumX, sumY, sumXX, sumXY); - BUCKETING
    //getRes2Regression(n, sumX, sumY, result[1])
    /*
    public static double[] getPowerRegression(double[][] data) {
        int n = data.length;
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data."); }
        double sumX = 0;
        double sumY = 0;
        double sumXX = 0;
        double sumXY = 0;
        for (int i = 0; i < n; i++) {
            double x = Math.log(data[i][0]);
            double y = Math.log(data[i][1]);
            sumX += x;
            sumY += y;
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
            sumXY += xy;
        }
        double[] result = new double[2];
        result[1] = getRes1(n, sumX, sumY, sumXX, sumXY);
        result[0] = getRes2Regression(n, sumX, sumY, result[1]);
        return result;
    }
*/



    /*stage - o - original function */
/*
    public static double[] getPowerRegression(double[][] data) {

        int n = data.length;
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }
        double sumX = 0;
        double sumY = 0;
        double sumXX = 0;
        double sumXY = 0;
        for (int i = 0; i < n; i++) {
            double x = Math.log(data[i][0]);
            double y = Math.log(data[i][1]);
            sumX += x;
            sumY += y;
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
            sumXY += xy;
        }
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double xbar = sumX / n;
        double ybar = sumY / n;

        double[] result = new double[2];
        result[1] = sxy / sxx;
        result[0] = Math.pow(Math.exp(1.0), ybar - result[1] * xbar);

        return result;

    }
*/

    /*stage 0 - initiate errors for tests
    try 1 - replace order sumY before sumX - work ok as should
    try 2 - replace order sumXY before sumX- work ok as should
    try 3 - replace order sumXX before sumX - work ok as should
    try 4 - replace order sumXY before sumY - work ok as should
    try 5 - change value of x - fail as should - 2 errors in tests
    try - 6 change value of y - fail as should - 2 errors in tests
     */
   /* public static double[] getPowerRegression(double[][] data) {

        int n = data.length;
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }

        double sumX = 0;
        double sumY = 0;
        double sumXX = 0;
        double sumXY = 0;
        for (int i = 0; i < n; i++) {
            double x = Math.log(data[i][0]);
           double y = Math.log(data[i][1]);
            sumX += x;
            sumY += y;
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
           sumXY += xy;
        }
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double xbar = sumX / n;
        double ybar = sumY / n;

        double[] result = new double[2];
        result[1] = sxy / sxx;
        result[0] = Math.pow(Math.exp(1.0), ybar - result[1] * xbar);

        return result;

    }
    */


   //change - for to while
    //new orig code - we aer looking on lines: 236 - 250
   /* public static double[] getPowerRegression(double[][] data) {
        int n = data.length;
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }
        double sumX = 0;
        double sumY = 0;
        double sumXX = 0;
        double sumXY = 0;
        int i = 0;
        while(i<n) {
           double x = Math.log(data[i][0]);
           double y = Math.log(data[i][1]);
           sumX += x;
           sumY += y;
           double xx = x * x;
           sumXX += xx;
           double xy = x * y;
           sumXY += xy;
           i++;
        }
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double xbar = sumX / n;
        double ybar = sumY / n;
        double[] result = new double[2];
        result[1] = sxy / sxx;
        result[0] = Math.pow(Math.exp(1.0), ybar - result[1] * xbar);
        return result;
    }*/


   /* V = {sumX}
   before change back to for
    */
   /*
    public static double[] getPowerRegression(double[][] data) {
        int n = data.length;
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }
        //slice
        double sumX = 0;
        int i = 0;
        while(i<n) {
            double x = Math.log(data[i][0]);
            sumX += x;
            i++;
        }
        //co slice
        double sumY = 0;
        double sumXX = 0;
        double sumXY = 0;
        int i = 0;
        while(i<n) {
            double x = Math.log(data[i][0]);
            double y = Math.log(data[i][1]);
            sumY += y;
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
            sumXY += xy;
            i++;
        }
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double xbar = sumX / n;
        double ybar = sumY / n;
        double[] result = new double[2];
        result[1] = sxy / sxx;
        result[0] = Math.pow(Math.exp(1.0), ybar - result[1] * xbar);
        return result;
    }
*/



    /* V = {sumX}
   after change back to for loop
    */
    /*
    public static double[] getPowerRegression(double[][] data) {
        int n = data.length;
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }
        //slice
        double sumX = 0;
        for (int i = 0; i < n; i++)  {
            double x = Math.log(data[i][0]);
            sumX += x;
        }
        //co slice
        double sumY = 0;
        double sumXX = 0;
        double sumXY = 0;
        for (int i = 0; i < n; i++)  {
            double x = Math.log(data[i][0]);
            double y = Math.log(data[i][1]);
            sumY += y;
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
            sumXY += xy;
        }
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double xbar = sumX / n;
        double ybar = sumY / n;
        double[] result = new double[2];
        result[1] = sxy / sxx;
        result[0] = Math.pow(Math.exp(1.0), ybar - result[1] * xbar);
        return result;
    }
*/


     /* V = {sumY}
   after change for to while - before sliding
    */
     /*
   public static double[] getPowerRegression(double[][] data) {
        int n = data.length;
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }
        double sumX = 0;
        for (int i = 0; i < n; i++)  {
            double x = Math.log(data[i][0]);
            sumX += x;
        }
        //V = {sumY}
        double sumY = 0;
        double sumXX = 0;
        double sumXY = 0;
        int i = 0;
        while(i<n) {
            double x = Math.log(data[i][0]);
            double y = Math.log(data[i][1]);
            sumY += y;
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
            sumXY += xy;
            i++;
        }
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double xbar = sumX / n;
        double ybar = sumY / n;
        double[] result = new double[2];
        result[1] = sxy / sxx;
        result[0] = Math.pow(Math.exp(1.0), ybar - result[1] * xbar);
        return result;
    }
*/


    /* V = {sumY}
    result sliding -
     */
    /*
    public static double[] getPowerRegression(double[][] data) {
        int n = data.length;
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }
        double sumX = 0;
        for (int i = 0; i < n; i++)  {
            double x = Math.log(data[i][0]);
            sumX += x;
        }
        //slice
        double sumY = 0;
        int i = 0;
        while(i<n) {
            double y = Math.log(data[i][1]);
            sumY += y;

            i++;
        }
        //co - slice
        double sumXX = 0;
        double sumXY = 0;
        int i = 0;
        while(i<n) {
            double x = Math.log(data[i][0]);
            double y = Math.log(data[i][1]);
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
            sumXY += xy;
            i++;
        }
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double xbar = sumX / n;
        double ybar = sumY / n;
        double[] result = new double[2];
        result[1] = sxy / sxx;
        result[0] = Math.pow(Math.exp(1.0), ybar - result[1] * xbar);
        return result;
    }
*/



    /* V = {sumY}
    result sliding - after changing while back to for
     */
    /*
    public static double[] getPowerRegression(double[][] data) {
        int n = data.length;
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }
        double sumX = 0;
        for (int i = 0; i < n; i++)  {
            double x = Math.log(data[i][0]);
            sumX += x;
        }
        //slice
        double sumY = 0;
        for (int i = 0; i < n; i++)  {
            double y = Math.log(data[i][1]);
            sumY += y;
        }
        //co - slice
        double sumXX = 0;
        double sumXY = 0;
        for (int i = 0; i < n; i++)  {
            double x = Math.log(data[i][0]);
            double y = Math.log(data[i][1]);
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
            sumXY += xy;
        }
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double xbar = sumX / n;
        double ybar = sumY / n;
        double[] result = new double[2];
        result[1] = sxy / sxx;
        result[0] = Math.pow(Math.exp(1.0), ybar - result[1] * xbar);
        return result;
    }
*/



     /* V = {sumXX}
     */
     /*
    public static double[] getPowerRegression(double[][] data) {
        int n = data.length;
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }
        double sumX = 0;
        for (int i = 0; i < n; i++)  {
            double x = Math.log(data[i][0]);
            sumX += x;
        }
        double sumY = 0;
        for (int i = 0; i < n; i++)  {
            double y = Math.log(data[i][1]);
            sumY += y;
        }
        // V={sumY}
        double sumXX = 0;
        double sumXY = 0;
        for (int i = 0; i < n; i++)  {
            double x = Math.log(data[i][0]);
            double y = Math.log(data[i][1]);
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
            sumXY += xy;
        }
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double xbar = sumX / n;
        double ybar = sumY / n;
        double[] result = new double[2];
        result[1] = sxy / sxx;
        result[0] = Math.pow(Math.exp(1.0), ybar - result[1] * xbar);
        return result;
    }
*/



    /* V = {sumXX}
    after changing for to while
     */
    /*
    public static double[] getPowerRegression(double[][] data) {
        int n = data.length;
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }
        double sumX = 0;
        for (int i = 0; i < n; i++)  {
            double x = Math.log(data[i][0]);
            sumX += x;
        }
        double sumY = 0;
        for (int i = 0; i < n; i++)  {
            double y = Math.log(data[i][1]);
            sumY += y;
        }
        // V={sumY}
        double sumXX = 0;
        double sumXY = 0;
        int i = 0;
        while(i<n)  {
            double x = Math.log(data[i][0]);
            double y = Math.log(data[i][1]);
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
            sumXY += xy;
            i++;
        }
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double xbar = sumX / n;
        double ybar = sumY / n;
        double[] result = new double[2];
        result[1] = sxy / sxx;
        result[0] = Math.pow(Math.exp(1.0), ybar - result[1] * xbar);
        return result;
    }*/



     /* V = {sumXX}
    result of sliding
     */
     /*
    public static double[] getPowerRegression(double[][] data) {
        int n = data.length;
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }
        double sumX = 0;
        for (int i = 0; i < n; i++)  {
            double x = Math.log(data[i][0]);
            sumX += x;
        }
        double sumY = 0;
        for (int i = 0; i < n; i++)  {
            double y = Math.log(data[i][1]);
            sumY += y;
        }
        // slice
        double sumXX = 0;
        int i = 0;
        while(i<n)  {
            double x = Math.log(data[i][0]);
            double xx = x * x;
            sumXX += xx;
            i++;
        }
        //co slice
        double sumXY = 0;
        int i = 0;
        while(i<n)  {
            double x = Math.log(data[i][0]);
            double y = Math.log(data[i][1]);
            double xy = x * y;
            sumXY += xy;
            i++;
        }
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double xbar = sumX / n;
        double ybar = sumY / n;
        double[] result = new double[2];
        result[1] = sxy / sxx;
        result[0] = Math.pow(Math.exp(1.0), ybar - result[1] * xbar);
        return result;
    }
*/


      /* V = {sumXX}
    result of sliding - after changing while to for back
     */
/*
    public static double[] getPowerRegression(double[][] data) {
        int n = data.length;
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }
        double sumX = 0;
        for (int i = 0; i < n; i++)  {
            double x = Math.log(data[i][0]);
            sumX += x;
        }
        double sumY = 0;
        for (int i = 0; i < n; i++)  {
            double y = Math.log(data[i][1]);
            sumY += y;
        }
        // slice
        double sumXX = 0;
        for (int i = 0; i < n; i++)   {
            double x = Math.log(data[i][0]);
            double xx = x * x;
            sumXX += xx;
        }
        //co slice
        double sumXY = 0;
        for (int i = 0; i < n; i++)  {
            double x = Math.log(data[i][0]);
            double y = Math.log(data[i][1]);
            double xy = x * y;
            sumXY += xy;
        }
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double xbar = sumX / n;
        double ybar = sumY / n;
        double[] result = new double[2];
        result[1] = sxy / sxx;
        result[0] = Math.pow(Math.exp(1.0), ybar - result[1] * xbar);
        return result;
    }
*/



  /*
    Final split loop code
     */
/*
    public static double[] getPowerRegression(double[][] data) {
        int n = data.length;
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }
        double sumX = 0;
        for (int i = 0; i < n; i++)  {
            double x = Math.log(data[i][0]);
            sumX += x;
        }
        double sumY = 0;
        for (int i = 0; i < n; i++)  {
            double y = Math.log(data[i][1]);
            sumY += y;
        }
        double sumXX = 0;
        for (int i = 0; i < n; i++)   {
            double x = Math.log(data[i][0]);
            double xx = x * x;
            sumXX += xx;
        }
        double sumXY = 0;
        for (int i = 0; i < n; i++)  {
            double x = Math.log(data[i][0]);
            double y = Math.log(data[i][1]);
            double xy = x * y;
            sumXY += xy;
        }
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double xbar = sumX / n;
        double ybar = sumY / n;
        double[] result = new double[2];
        result[1] = sxy / sxx;
        result[0] = Math.pow(Math.exp(1.0), ybar - result[1] * xbar);
        return result;
    }
*/

/*
    Final split loop code - with Extract Method
     */
/*
    public static double[] getPowerRegression(double[][] data) {
        int n = data.length;
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }
        double sumX = getSumX(data, n);
        double sumY = getSumY(data, n);
        double sumXX = getSumXX(data, n);
        double sumXY = getSumXY(data, n);
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double xbar = sumX / n;
        double ybar = sumY / n;
        double[] result = new double[2];
        result[1] = sxy / sxx;
        result[0] = Math.pow(Math.exp(1.0), ybar - result[1] * xbar);
        return result;
    }
    */

    /*
    Final split loop code - with Extract Method - and replace all used with calls
     */

    public static double[] getPowerRegression(double[][] data) {
        int n = data.length;
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }
        double sxx = getSumXX(data, n) - (getSumX(data, n) * getSumX(data, n)) / n;
        double sxy = getSumXY(data, n) - (getSumX(data, n) * getSumY(data, n)) / n;
        double xbar = getSumX(data, n) / n;
        double ybar = getSumY(data, n) / n;
        double[] result = new double[2];
        result[1] = sxy / sxx;
        result[0] = Math.pow(Math.exp(1.0), ybar - result[1] * xbar);
        return result;
    }


    private static double getSumXY(double[][] data, int n) {
        double sumXY = 0;
        for (int i = 0; i < n; i++)  {
            double x = Math.log(data[i][0]);
            double y = Math.log(data[i][1]);
            double xy = x * y;
            sumXY += xy;
        }
        return sumXY;
    }

    private static double getSumXX(double[][] data, int n) {
        double sumXX = 0;
        for (int i = 0; i < n; i++)   {
            double x = Math.log(data[i][0]);
            double xx = x * x;
            sumXX += xx;
        }
        return sumXX;
    }

    private static double getSumY(double[][] data, int n) {
        double sumY = 0;
        for (int i = 0; i < n; i++)  {
            double y = Math.log(data[i][1]);
            sumY += y;
        }
        return sumY;
    }

    private static double getSumX(double[][] data, int n) {
        double sumX = 0;
        for (int i = 0; i < n; i++)  {
            double x = Math.log(data[i][0]);
            sumX += x;
        }
        return sumX;
    }


    /**
     * Returns the parameters 'a' and 'b' for an equation y = ax^b, fitted to 
     * the data using a power regression equation.  The result is returned as 
     * an array, where double[0] --> a, and double[1] --> b.
     *
     * @param data  the data.
     * @param series  the series to fit the regression line against.
     *
     * @return The parameters.
     */
    //original - code elimination
    /*
    public static double[] getPowerRegression(XYDataset data, int series) {

        int n = data.getItemCount(series);
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }
        double sumX = 0;
        double sumY = 0;
        double sumXX = 0;
        double sumXY = 0;
        for (int i = 0; i < n; i++) {
            double x = Math.log(data.getXValue(series, i));
            double y = Math.log(data.getYValue(series, i));
            sumX += x;
            sumY += y;
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
            sumXY += xy;
        }
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double xbar = sumX / n;
        double ybar = sumY / n;

        double[] result = new double[2];
        result[1] = sxy / sxx;
        result[0] = Math.pow(Math.exp(1.0), ybar - result[1] * xbar);

        return result;

    }*/


    //original - code elimination - change result to temp
    /*
    public static double[] getPowerRegression(XYDataset data, int series) {
        int n = data.getItemCount(series);
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }
        double sumX = 0;
        double sumY = 0;
        double sumXX = 0;
        double sumXY = 0;
        for (int i = 0; i < n; i++) {
            double x = Math.log(data.getXValue(series, i));
            double y = Math.log(data.getYValue(series, i));
            sumX += x;
            sumY += y;
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
            sumXY += xy;
        }
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double xbar = sumX / n;
        double ybar = sumY / n;
        double res1 = sxy / sxx;
        double res2 = Math.pow(Math.exp(1.0), ybar - res1 * xbar);
        double[] result = new double[2];
        result[1] = res1;
        result[0] = res2;
        return result;

    }
*/

    //result bucketing
    /*
    public static double[] getPowerRegression(XYDataset data, int series) {
        int n = data.getItemCount(series);
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }
        double sumX = 0;
        double sumY = 0;
        double sumXX = 0;
        double sumXY = 0;
        for (int i = 0; i < n; i++) {
            double x = Math.log(data.getXValue(series, i));
            double y = Math.log(data.getYValue(series, i));
            sumX += x;
            sumY += y;
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
            sumXY += xy;
        }
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        double res1 = sxy / sxx;
        double xbar = sumX / n;
        double ybar = sumY / n;
        double res2 = Math.pow(Math.exp(1.0), ybar - res1 * xbar);
        double[] result = new double[2];
        result[1] = res1;
        result[0] = res2;
        return result;

    }*/



    //result bucketing - extract methods
    /*
    public static double[] getPowerRegression(XYDataset data, int series) {
        int n = data.getItemCount(series);
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data.");
        }
        double sumX = 0;
        double sumY = 0;
        double sumXX = 0;
        double sumXY = 0;
        for (int i = 0; i < n; i++) {
            double x = Math.log(data.getXValue(series, i));
            double y = Math.log(data.getYValue(series, i));
            sumX += x;
            sumY += y;
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
            sumXY += xy;
        }
        double res1 = getRes1(n, sumX, sumY, sumXX, sumXY);
        double res2 = getRes2Regression(n, sumX, sumY, res1);
        double[] result = new double[2];
        result[1] = res1;
        result[0] = res2;
        return result;
    }
*/

    //result bucketing - extract methods - back to orig code - final version
    public static double[] getPowerRegression(XYDataset data, int series) {
        int n = data.getItemCount(series);
        if (n < 2) {
            throw new IllegalArgumentException("Not enough data."); }
        double sumX = 0;
        double sumY = 0;
        double sumXX = 0;
        double sumXY = 0;
        for (int i = 0; i < n; i++) {
            double x = Math.log(data.getXValue(series, i));
            double y = Math.log(data.getYValue(series, i));
            sumX += x;
            sumY += y;
            double xx = x * x;
            sumXX += xx;
            double xy = x * y;
            sumXY += xy; }
        double[] result = new double[2];
        result[1] = getRes1(n, sumX, sumY, sumXX, sumXY);
        result[0] = getRes2Regression(n, sumX, sumY, result[1]);
        return result;
    }

    private static double getRes2Regression(int n, double sumX, double sumY, double res1) {
        double xbar = sumX / n;
        double ybar = sumY / n;
        return Math.pow(Math.exp(1.0), ybar - res1 * xbar);
    }

    private static double getRes1(int n, double sumX, double sumY, double sumXX, double sumXY) {
        double sxx = sumXX - (sumX * sumX) / n;
        double sxy = sumXY - (sumX * sumY) / n;
        return sxy / sxx;
    }


}
