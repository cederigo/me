%Test data
%Y = [ 0 0; 0 1];
%N = [ 1 0; 1 1];
Y = load('tr-j.txt');
N = load('tr-n.txt');
ysize = size(Y,1); 
nsize = size(N,1);

%"ausreisser"
Y = min(Y,5);
N = min(N,5);

YN = [ Y ones(ysize,1); N ones(nsize,1)];


% inv(E(xx')) in script
E1 = (ysize+nsize) * inv(YN' * YN);

% E(xd(x)')
ID = [ ones(ysize,1) zeros(ysize,1);
      zeros(nsize,1) ones(nsize,1)];

E2 = YN' * ID / (ysize+nsize);

W = E1 * E2;

% Trenngerade
% d1(x) -d2(x) = 0
D12 = W(:,1)-W(:,2);

% Test mit trenngerade
% Muste überall x1 und x2 vertauschen. wiso??
x1 = Y(:,1);
x2 = Y(:,2);
test = D12(1).*x2 + D12(2).*x1 + D12(3);
correctYes = size(find(test > 0),1);

x1 = N(:,1);
x2 = N(:,2);
test = D12(1).*x2 + D12(2).*x1 + D12(3);
correctNo  = size(find(test < 0),1);

fprintf('correct classifications: %d / %d\n',(correctYes + correctNo),(ysize+nsize));


% plotting
x2 = [0:0,5:5];
x1 = (-D12(1)*x2 - D12(3)) / D12(2);

hold on
xlabel('h=l/r');
ylabel('v=t/b');
plot(Y(:,1),Y(:,2),'or');
plot(N(:,1),N(:,2),'xb');
plot(x1,x2,'g');
hold off



