#!usr/bin/env python
import csv
def main():

    data = []
    with open("prime_check_results.txt", 'r') as file:
        for line in file:
            parts = line.strip().split(' - ')
            if parts[1] == 'true':
                number = int(parts[0])
                paso1 = int(parts[2].split()[-1])
                paso2 = int(parts[3].split()[-1])
                paso3 = int(parts[4].split()[-1])
                paso5 = int(parts[5].split()[-1])
                time_pasos = int(parts[6].split()[-1])
                time = int(parts[7].split()[-1])
                num_digits = len(str(number))
                data.append((number, paso1, paso2, paso3, paso5, time_pasos, time, num_digits))

    with open("prime_check_results.csv", 'w', newline='') as file:
        writer = csv.writer(file)
        writer.writerow(['n', 'paso1', 'paso2', 'paso3', 'paso5', 't_pasos', 't', 'digitos'])
        writer.writerows(data)

if __name__ == "__main__":
    main()