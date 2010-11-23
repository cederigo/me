X = load('pcaData.txt');
%normalize data to interval [0 1]
mins = min(X);
mins = mins(ones(size(X,1),1),:);
maxs = max(X);
maxs = maxs(ones(size(X,1),1),:);
X = (X - mins) ./ (maxs -mins);

%pca normalize
mj = sum(X,1) / size(X,1);
mj = mj(ones(size(X,1),1),:);
X = X - mj;

%mittel-vektor
m = sum(X) / size(X,1);
% Kovarianz-matrix
K = (X' * X) - m'*m;
[V,D] = eig(K);

% take 3 highest eigenvalues's corresponding vectors
% hard coded sorry
E = V(:,[15 16 17]);

%new subspace
Y = E' * X';
Y = Y';

%plot3(Y(:,1),Y(:,2),Y(:,3),'+b');

%project x-y
%plot(Y(:,1),Y(:,2),'+b');

%project x-z
plot(Y(:,1),Y(:,3),'+b');

%project y-z
%plot(Y(:,2),Y(:,3),'+b');


