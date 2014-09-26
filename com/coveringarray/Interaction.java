package com.coveringarray;

import com.coveringarray.CoveringArrayParameters;

/**
* Class to manage storage of interactions
* and conversion between formats.
*/
public class Interaction
{
    /**
    * The column pair of this interaction.
    */
    public final int columns[];
    
    /**
    * The symbols at the columns for this interaction.
    */
    public final int symbols[];
    
    /**
    * Creates an interaction.
    * @param columns the column pair of this interaction.
    * @param symbols the symbols at the columns of this interaction.
    */
    public Interaction(final int columns[], final int symbols[])
    {
        this.columns = columns;
        this.symbols = symbols;
    }
    
    /**
    * Creates an interaction from the covering array.
    * @param columns the column pair of this interaction.
    * @param parameters the covering array for which the interaction must be formed.
    */
    public Interaction(final int columns[], final CoveringArrayParameters parameters, int row)
    {
        this.columns = columns;
        this.symbols = new int[this.columns.length];
        
        for(int i=0;i<this.symbols.length;i++)
        this.symbols[i] = parameters.CA[row][this.columns[i]];
    }

    /**
    * Used as a return value to indicate the interaction is a dont care.
    */
    public static final int DONT_CARE_I = -1;

    /**
    * Used as a return value to indicate the interaction is a dont care.
    */
    public static final String DONT_CARE_S = null;
    
    /**
    * Gets the Integer value of an interaction at the
    * specified row and columns of the covering array.
    * @param parameters  parameters of the covering array whose value is to be found. 
    * @param column_pair the columns for which the value is to be found.
    * @param row the row for which the value is to be found.
    * @return integer value of the interaction at the specified row and columns, 
    *         DONT_CARE is returned if the interaction is a don't care or there was an error.
    * <p> 
    *         An interaction is a don't care if any one of its symbols is a don't care.
    */
    public static int toInt(CoveringArrayParameters parameters, int[] column_pair, int row)
    {
    String interaction = "";
    int value = DONT_CARE_I;
    
        try
        {
        interaction = Interaction.toString(parameters, column_pair, row);
        value = Integer.parseInt(interaction, parameters.v);
        }
        catch(Exception e)
        {
        return DONT_CARE_I;
        }

    return value;
    }

    /**
    * Gets the String representation of an interaction at the specified row
    * and columns of the covering array.
    * @param parameters parameters of the covering array whose value is to be found.    
    * @param column_pair the columns for which the value is to be found.
    * @param row the row for which the value is to be found.
    * @return string value of the interaction at the specified row and columns, 
    *         null is returned if the interaction is a don't care or there was an error.    
    */ 
    public static String toString(CoveringArrayParameters parameters, int[] column_pair, int row)
    {
    String interaction = "";

        try
        {
            for(int i=0;i<parameters.t;i++)
            interaction += parameters.CA[row][column_pair[i]];
        }
        catch(Exception e)
        {
        return DONT_CARE_S;
        }   

    return interaction; 
    }
    
    public static long getColumnValue(int k, final int columns[])
    {
    long value = 0;
    
        for(int i=columns.length-1;i>=0;i--)
        value += (long)Math.pow(k, columns.length - 1 - i) * columns[i];
    
    return value;
    }

    public static int[] decodeColumnValue(long column_pair_value, int k, int t)
    {
    int column_pair[];
    int index;
    
        column_pair = new int[t];
        index = t-1;
        
        while(column_pair_value > 0)
        {
            column_pair[index--] = (int)(column_pair_value % k);
        
        column_pair_value /= k;
        }
        
    return column_pair;
    }

    public static long getOffsetStart(int k, int t, long v_power_t, int[] column_pair)
    {
    long index = 0;
    
        for(int i=0;i<t;i++)
        index += Math.pow(k,i) * column_pair[column_pair.length - 1 - i];

    return ((--index) * v_power_t);
    }
}
