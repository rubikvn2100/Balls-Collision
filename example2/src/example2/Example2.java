/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example2;

/**
 *
 * @author Rubikvn2100
 */
import java.util.Scanner;

public class Example2 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a positive number: ");
        int n = input.nextInt();
        
        int[] factors = factorization( n );
        print( factors );
    }
    
    static int[] factorization( int n )
    {
        int[] factors = new int[30];
        
        int count = 0;
        int i = 2;
        while( n > 1 )
        {
            if( n % i == 0 )
            {
                do
                {
                    n /= i;
                } while( n % i == 0 );
                
                factors[count++] = i;
            }
            i++;
        }
        
        int[] results = new int[count];
        for( i = 0; i < count; i++ )
        {
            results[i] = factors[i];
        }
        
        return results;
    }
    
    static void print( int[] factor )
    {
        int count = factor.length;
        for( int i = 0; i < count; i++ )
        {
            if( (i + 1) % 4 == 0 )
            {
                System.out.println(factor[i]);
            }
            else
            {
                System.out.print(factor[i] + "\t");
            }
        }
    }
}
