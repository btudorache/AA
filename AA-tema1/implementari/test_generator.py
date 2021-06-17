import random

CHANCE_TO_GENERATE_EDGE = 0.3
NEGATIVE_WEIGHTS = (-5, 95)
NORMAL_WEIGHTS = (1, 100)

NUM_NODES = 1000
FILE_NAME = 'acyclic10.txt'
IS_ACYCLIC = True
HAS_NEGATIVE_WEIGHTS = False


def generate_test():
    with open(f'in/{FILE_NAME}', 'a') as f:
        range_of_weights = NEGATIVE_WEIGHTS if HAS_NEGATIVE_WEIGHTS else NORMAL_WEIGHTS
        start, end = range_of_weights
        list_of_edges = []
        num_of_edges = 0

        if IS_ACYCLIC:
            for i in range(NUM_NODES):
                for j in range(i + 1, NUM_NODES):
                    if random.random() < CHANCE_TO_GENERATE_EDGE:
                        num_of_edges += 1
                        list_of_edges.append((i, j, random.randint(start, end)))
        else:
            for i in range(NUM_NODES):
                for j in range(NUM_NODES):
                    if i != j and random.random() < CHANCE_TO_GENERATE_EDGE:
                        num_of_edges += 1
                        list_of_edges.append((i, j, random.randint(start, end)))

        f.write(f'{NUM_NODES} {num_of_edges} {random.randint(0, (NUM_NODES // 2))}\n')
        for (start_node, end_node, weight) in list_of_edges:
            f.write(f'{start_node} {end_node} {weight}\n')


if __name__ == '__main__':
    generate_test()
