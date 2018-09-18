using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GameOfLife{

    public partial class Form1 : Form {

        /***************************************
         *          GLOBAL VARIABLES           *
         ***************************************/

        private bool[,] matrix;

        private int cellArea = 10;
        private int generation = 1;
        public Form1() {
            InitializeComponent();
            createMatrix(50, 50);
        }
        
        /// <summary>
        /// This function creates a matrix of bools which size it's n x m
        /// also this function adds an extra pair of cols and rows to 
        /// simulate a toroid   
        /// </summary>  
        /// <param name="rows"></param>
        /// <param name="cols"></param>
        private void createMatrix(int rows, int cols) {
            matrix = new bool[rows, cols];
            scrollBox();
        }

        private void PBAutomataSimulator_Paint(object sender, PaintEventArgs e){

            Graphics graphics = e.Graphics;
            Brush alive = Brushes.White;
            Brush dead = Brushes.Black;

            for (int row = 0; row < matrix.GetLength(0); row++) {

                for (int col = 0; col < matrix.GetLength(1); col++) {

                    Brush b;
                    
                    if (matrix[row, col])
                        b = alive;
                    else
                        b = dead;
                    
                    graphics.FillRectangle(b, row*cellArea, col*cellArea, cellArea, cellArea);

                }
            }
        }

        /// <summary>
        /// This funcion manipulates a matrix and evaluate it 
        /// using our rules. 
        /// </summary>
        /// <param name="p_matrix"></param>
        /// <returns>A matrix with the new generation data</returns>
        private bool[,] nextGeneration(bool[,] p_matrix) {

            /****************************************************
             *                  CONDITIONS                      *
             * **************************************************/

            /******************* X values ********************/
            int Xi = Int32.Parse( string.IsNullOrEmpty(ComboBXi.Text) ? "2" : ComboBXi.Text );

            /******************* Y values ********************/
            int Yi = Int32.Parse( string.IsNullOrEmpty(ComboBYi.Text) ? "3" : ComboBYi.Text );

            /******************* X2 values *******************/
            int X2i = Int32.Parse( (string.IsNullOrEmpty(ComboBX2i.Text) ? "3" : ComboBX2i.Text ) );

            /******************* Y2 values *******************/
            int Y2i = Int32.Parse( (string.IsNullOrEmpty(ComboBY2i.Text) ? "3" : ComboBY2i.Text) );

            bool[,] new_matrix = new bool[p_matrix.GetLength(0), p_matrix.GetLength(1)];
            //We check each cell from the original matrix and we substitute it
            for (int row = 0; row < p_matrix.GetLength(0); row++) {

                for (int col = 0; col < p_matrix.GetLength(1); col++) {
                    /**
                     * Here we need to evaluate using the rules given by input
                     **/
                    int neighbors = getAliveNeighbors(p_matrix, row, col);
                    if (p_matrix[row, col]){
                        new_matrix[row, col] = (neighbors >= Xi && neighbors <= Yi);
                    } else {
                        new_matrix[row, col] = (neighbors >= X2i && neighbors <= Y2i);
                    }

                }

            }
            updateTextGeneration();
            return new_matrix;
        }

        /// <summary>
        /// Gets information about the cells around a central cell. 
        /// Obviouslly the cells must to be alive.
        /// </summary>
        /// <param name="p_matrix">The actual matrix</param>
        /// <param name="p_row">row of the central cell</param>
        /// <param name="p_col">col of the central cell</param>
        /// <returns>Number of neighboors around the central cell (just living neighbors)</returns>
        private int getAliveNeighbors(bool[,] p_matrix, int p_row, int p_col) {

            int neighbors = 0;
            try
            {
                int max_rows = p_matrix.GetLength(0);
                int max_cols = p_matrix.GetLength(1);

                for (int row = -1; row <= 1; row++)
                {

                    for (int col = -1; col <= 1; col++)
                    {

                        int c_row = row + p_row;
                        int c_col = col + p_col;

                        //We are in the center cell
                        if (c_row == p_row && c_col == p_col)
                        {
                            continue;
                        }
                        if (c_row == -1 && c_col == -1 & p_matrix[max_rows - 1, 0])
                            neighbors++;
                        else if (c_row == max_rows && c_col == max_cols && p_matrix[0, 0])
                            neighbors++;
                        else if (c_row == -1 && c_col == max_cols && p_matrix[max_rows - 1, max_cols - 1])
                            neighbors++;
                        else if (c_row == max_rows && c_col == -1 && p_matrix[0, 0])
                            neighbors++;
                        else 

                        if (c_row == -1 && p_matrix[max_rows - 1, c_col])
                            neighbors++;
                        else if (c_row == max_rows && p_matrix[0, c_col])
                            neighbors++;
                        else if (c_col == -1 && p_matrix[c_row, max_cols - 1])
                            neighbors++;
                        else if (c_col == max_cols - 1 && p_matrix[c_row, 0])
                            neighbors++;

                        if (c_row < 0 || c_row >= max_rows)
                        {
                            continue;
                        }

                        if (c_col < 0 || c_col >= max_cols)
                        {
                            continue;
                        }

                        if (p_matrix[c_row, c_col])
                        {
                            neighbors++;
                        }

                    }

                }
            }
            catch (Exception e) {
                Console.WriteLine(e);
            }

            return neighbors;
        }

        private void step() {
            matrix = nextGeneration(matrix);
            countOnes();
            PBAutomataSimulator.Invalidate();
            
        }

        private void updateTextGeneration() {
            TXTGeneration.Text = "Generation: " + generation++;    
        }

        private void scrollBox(){
            PBAutomataSimulator.Size = new Size((matrix.GetLength(0)) * cellArea, (matrix.GetLength(1)) * cellArea);
            PBAutomataSimulator.SizeMode = PictureBoxSizeMode.AutoSize;
            flowLayoutPanel1.AutoScroll = true;
            flowLayoutPanel1.Controls.Add(PBAutomataSimulator);
        }

        private void countOnes() {
            int ones = 0;
            for (int x = 0; x < matrix.GetLength(0); x++) {

                for (int y = 0; y < matrix.GetLength(1); y++) {
                    if (matrix[x, y]) ones++; 
                }
            }
            CHHistogram.Series["#Ones"].Points.AddY(ones);
            TXTPopulation.Text = "Population " + ones;
        }
        /*****************************************************
         *                A better algorithm                 *
         *****************************************************/


        /*****************************************************
         *                A better algorithm                 *
         *****************************************************/





        /*****************************************************
         *                      Events                       *
         *****************************************************/

        private void BTNStep_Click(object sender, EventArgs e){
            step();
        }

        private void PBAutomataSimulator_MouseDown(object sender, MouseEventArgs e){
            int x = e.X / cellArea;
            int y = e.Y / cellArea;
            matrix[x, y] = !matrix[x,y];
            PBAutomataSimulator.Invalidate();
        }

        private void BTNStart_Click(object sender, EventArgs e){
            if (BTNStart.Text == "Start"){
                TimerSimulation.Start();
                BTNStart.Text = "Stop";
            }
            else{
                TimerSimulation.Stop();
                BTNStart.Text = "Start";
            }
        }

        private void trackBar1_ValueChanged(object sender, EventArgs e){
            TimerSimulation.Interval = TBSpeed.Value;
        }

        private void TimerSimulation_Tick(object sender, EventArgs e){
            step();
        }

        private void BTNZoomP_Click(object sender, EventArgs e){
            if (cellArea < 20) {
                cellArea++;
                PBAutomataSimulator.Invalidate();
                scrollBox();
            }
        }

        private void BTNZoomM_Click(object sender, EventArgs e){
            if (cellArea > 1 ) {
                cellArea--;
                PBAutomataSimulator.Invalidate();
                scrollBox();
            }
        }
    }
}