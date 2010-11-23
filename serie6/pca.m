X = load('pcaData.txt');
n = 3;
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
% eigenvektoren, eigenwerte
[V,D] = eig(K);
D_MAX = max(D,[],2);
%used to hold indexes of eigenvectors
V_MAX_IND = 1:n;
% take 3 highest eigenvalues's
for i = V_MAX_IND
    [val, ind] = max(D_MAX);
    V_MAX_IND(i) = ind;    
    D_MAX(ind) = -1;    
end

E = V(:,V_MAX_IND);

%new subspace
Y = E' * X';
Y = Y';

plot3(Y(:,1),Y(:,2),Y(:,3),'+b');

%project x-y
%plot(Y(:,1),Y(:,2),'+b');

%project x-z
%plot(Y(:,1),Y(:,3),'+b');

%project y-z
%plot(Y(:,2),Y(:,3),'+b');


