using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Plotter
{
    public partial class Form1 : Form
    {

        /**************************************************
         *                GLOBAL VARIABLES                *
         * ************************************************/

        /*This variable stores the possibles states on our cellular automata
         *Dictionary variable works like a HashMap on Java. For our case we
         *gonna use an integer value as a key, this key we can got it
         *making a conversion from a binary string                          */
        private Dictionary<string, Object> data;
        private bool[,] matrix;
        /// <summary>
        /// Constructor
        /// </summary>
        public Form1()
        {
            InitializeComponent();
            //patternRecognition();
            //Initialize our dictionary
            data = new Dictionary<string, object>();
        }

        /**************************************************
         *                 MY OWN METHODS                 *
         * ************************************************/

        private void createMatrix(int rows, int cols) {
            matrix = new bool[cols, rows];
            for (int i = 0; i < cols; i++) {

                for (int j = 0; j < rows; j++) {
                    matrix[j, i] = true;
                }
            }
        }


        private void createSpace(int rows, int cols) {
            int[,] sub_matrix = new int[cols, rows];
            for (int x = 0; x < cols; x++) {

                for (int y = 0; y < rows; y++) {

                    /****************************************
                     *                CORNERS               *
                     ****************************************/
                    //Up-left
                    if (x == 0 && y == 0) {

                    }
                    //Down-right
                    else if (x == (cols - 1) && y == (rows - 1)) {

                    }
                    //Up-right
                    else if (x == 0 && y == (rows - 1)) {

                    }
                    //Down-left
                    else if (x == (cols - 1) && y == 0) {

                    }
                    /****************************************
                     *                 EDGES                *
                     ****************************************/
                    //Up
                    else if (y == 0) {
                    }
                    //Right
                    else if (y == (rows - 1)) {
                    }
                    //Down
                    else if (x == 0) {
                    }
                    //Left
                    else if (x == (cols - 1)) {
                    }
                }

            }

        }

        private bool[,] increseSpace(bool[,] matrix) {

            int cols = matrix.GetLength(0) + 2;
            int rows = matrix.GetLength(1) + 2;

            bool[] new_matrix = new bool[rows, cols];

        }
        private void patternRecognition(int x, int y, int dimension){

            string str = "";

            for (int i = 0; i < x; i++) {

                for (int j = 0; j < y; j++) {

                    try {
                        str += ((matrix[i, j]) ? "1" : "0");
                        data.Add(str, "");
                    } catch (ArgumentException) {
                        Console.WriteLine("The key " + str + " already exists ");
                    } catch (Exception e) {
                        Console.WriteLine("An exception has occurred on patternRecognition function: " + e);
                    }
                }
            }

        }
    }
}
