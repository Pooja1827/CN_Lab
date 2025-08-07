#include <stdio.h> 
#include <math.h> 
#include <stdlib.h> 
int main() { 
int i, j, k, count, err_pos = 0, flag = 0; 
char dw[20], cw[20], data[20]; 
printf("Enter data as binary bit stream (7 bits):\n"); 
scanf("%s", data); 
// Generating the codeword with parity bits 
for (i = 1, j = 0, k = 0; i < 12; i++) { 
if (i == (int)pow(2, j)) { 
dw[i] = '?'; 
j++; 
} else { 
dw[i] = data[k]; 
k++; 
} 
} 
// Calculating parity bits 
for (i = 0; i < 4; i++) { 
count = 0; 
for (j = (int)pow(2, i); j < 12; j += (int)pow(2, i) * 2) { 
for (k = 0; k < (int)pow(2, i) && j + k < 12; k++) { 
if (dw[j + k] == '1') count++; 
} 
} 
dw[(int)pow(2, i)] = (count % 2 == 0) ? '0' : '1'; 
} 
// Printing the generated codeword 
printf("Generated code word is:\n"); 
for (i = 1; i < 12; i++) { 
printf("%c", dw[i]); 
} 
printf("\n\nEnter the received Hamming code:\n"); 
scanf("%s", cw); 
// Adjusting the index for 1-based numbering 
for (i = 12; i > 0; i--) { 
cw[i] = cw[i - 1]; 
} 
// Error detection 
for (i = 0; i < 4; i++) { 
count = 0; 
for (j = (int)pow(2, i); j < 12; j += (int)pow(2, i) * 2) { 
for (k = 0; k < (int)pow(2, i) && j + k < 12; k++) { 
if (cw[j + k] == '1') count++; 
} 
} 
if (count % 2 != 0) { 
err_pos = err_pos + (int)pow(2, i); 
} 
} 
// Error correction 
if (err_pos == 0) { 
printf("\n\nThere is no error in the received code word.\n"); 
} else { 
if (cw[err_pos] == dw[err_pos]) { 
printf("\n\nThere are 2 or more errors in the received code...\n"); 
printf("Sorry...! Hamming code cannot correct 2 or more errors.\n"); 
flag = 1; 
} else { 
printf("\n\nThere is an error in bit position %d of the received code word.\n", err_pos); 
if (flag == 0) { 
cw[err_pos] = (cw[err_pos] == '1') ? '0' : '1'; 
printf("\n\nCorrected code word is:\n"); 
for (i = 1; i < 12; i++) { 
printf("%c", cw[i]); 
} 
} 
} 
} 
printf("\n\n"); 
return 0; 
}
